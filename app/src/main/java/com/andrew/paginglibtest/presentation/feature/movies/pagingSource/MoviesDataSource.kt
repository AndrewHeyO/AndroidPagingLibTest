package com.andrew.paginglibtest.presentation.feature.movies.pagingSource

import android.arch.paging.PageKeyedDataSource
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import com.andrew.paginglibtest.presentation.eventBus.State
import com.andrew.paginglibtest.presentation.eventBus.StateEventBus
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

/**
 * Created by Andrew on 06.06.2018.
 */

class MoviesDataSource constructor(private var repository: MoviesRepository,
                                   private var compositeDisposable: CompositeDisposable,
                                   private var stateEventBus: StateEventBus)
    : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        compositeDisposable.add(repository.getMovies(1)
                .doOnSubscribe { stateEventBus.onNext(State(State.Type.LOADING)) }
                .doOnSuccess { stateEventBus.onNext(State(State.Type.NONE)) }
                .subscribe({
                    callback.onResult(it, null, 2)
                    retryCompletable = null
                }, {
                    stateEventBus.onNext(State(State.Type.ERROR, it))
                    retryCompletable = Completable.fromAction { loadInitial(params, callback) }
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        compositeDisposable.add(repository.getMovies(params.key)
                .doOnSubscribe { stateEventBus.onNext(State(State.Type.LOADING)) }
                .doOnSuccess { stateEventBus.onNext(State(State.Type.NONE)) }
                .subscribe({
                    callback.onResult(it, params.key + 1)
                    retryCompletable = null
                }, {
                    stateEventBus.onNext(State(State.Type.ERROR, it))
                    retryCompletable = Completable.fromAction { loadAfter(params, callback) }
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    private var retryCompletable: Completable? = null

    fun reload() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .subscribe({ }, { stateEventBus.onNext(State(State.Type.ERROR, it)) }))
        }
    }

    private fun setRetry(action: Action) {
        this.retryCompletable = Completable.fromAction(action)
    }
}