package com.andrew.paginglibtest.presentation.feature.movies.pagingSource

import android.arch.paging.PageKeyedDataSource
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import com.andrew.paginglibtest.presentation.eventBus.ErrorEventBus
import com.andrew.paginglibtest.utils.logger.Logger
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

/**
 * Created by Andrew on 06.06.2018.
 */

class MoviesDataSource constructor(private var repository: MoviesRepository,
                                   private var compositeDisposable: CompositeDisposable,
                                   private var logger: Logger,
                                   private var errorEventBus: ErrorEventBus)
    : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        compositeDisposable.add(repository.getMovies(1)
                .subscribeOn(Schedulers.newThread())
                .doOnSuccess { logger.log("PAGE = " + 1 + " SIZE = " + it.size) }
                .subscribe({
                    callback.onResult(it, null, 2)
                    retryCompletable = null
                }, {
                    errorEventBus.onNext(it)
                    retryCompletable = Completable.fromAction({ loadInitial(params, callback) })
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        compositeDisposable.add(repository.getMovies(params.key)
                .subscribeOn(Schedulers.newThread())
                .doOnSuccess { logger.log("PAGE = " + params.key + " SIZE = " + it.size) }
                .subscribe({
                    callback.onResult(it, params.key + 1)
                    retryCompletable = null
                }, {
                    errorEventBus.onNext(it)
                    retryCompletable = Completable.fromAction({ loadAfter(params, callback) })
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }


    private var retryCompletable: Completable? = null

    public fun reload() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .subscribe({ }, { logger.log(it) }))
        }
    }

    private fun setRetry(action: Action) {
        this.retryCompletable = Completable.fromAction(action)
    }
}