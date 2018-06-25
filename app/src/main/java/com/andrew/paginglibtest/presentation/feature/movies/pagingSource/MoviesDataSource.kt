package com.andrew.paginglibtest.presentation.feature.movies.pagingSource

import android.arch.paging.PageKeyedDataSource
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import com.andrew.paginglibtest.presentation.viewModels.State
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by Andrew on 06.06.2018.
 */

@PerActivity
class MoviesDataSource @Inject constructor(private val repository: MoviesRepository,
                                           private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, Movie>() {

    private val subject: PublishSubject<State> = PublishSubject.create()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        compositeDisposable.add(repository.getMovies(1)
                .doOnSubscribe { subject.onNext(State(State.Type.LOADING)) }
                .doOnSuccess { subject.onNext(State(State.Type.NONE)) }
                .subscribe({
                    callback.onResult(it, null, 2)
                    retryCompletable = null
                }, {
                    subject.onNext(State(State.Type.ERROR, it))
                    retryCompletable = Completable.fromAction { loadInitial(params, callback) }
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        compositeDisposable.add(repository.getMovies(params.key)
                .doOnSubscribe { subject.onNext(State(State.Type.LOADING)) }
                .doOnSuccess { subject.onNext(State(State.Type.NONE)) }
                .subscribe({
                    callback.onResult(it, params.key + 1)
                    retryCompletable = null
                }, {
                    subject.onNext(State(State.Type.ERROR, it))
                    retryCompletable = Completable.fromAction { loadAfter(params, callback) }
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    fun observeState(): Observable<State> = subject

    private var retryCompletable: Completable? = null

    fun reload() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .subscribe({ }, { subject.onNext(State(State.Type.ERROR, it)) }))
        }
    }
}