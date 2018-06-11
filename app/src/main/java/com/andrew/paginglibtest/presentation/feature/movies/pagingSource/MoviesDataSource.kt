package com.andrew.paginglibtest.presentation.feature.movies.pagingSource

import android.arch.paging.PageKeyedDataSource
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import com.andrew.paginglibtest.utils.logger.Logger
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Andrew on 06.06.2018.
 */

class MoviesDataSource constructor(private var repository: MoviesRepository,
                                   private var compositeDisposable: CompositeDisposable,
                                   private var logger: Logger)
    : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        compositeDisposable.add(repository.getMovies(1)
                .doOnSuccess { logger.log("PAGE = " + 1 + " SIZE = " + it.size) }
                .subscribe({ callback.onResult(it, null, 2) },
                        { it.printStackTrace() }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        compositeDisposable.add(repository.getMovies(params.key)
                .doOnSuccess { logger.log("PAGE = " + params.key + " SIZE = " + it.size) }
                .subscribe({ callback.onResult(it, params.key + 1) },
                        { it.printStackTrace() }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }
}