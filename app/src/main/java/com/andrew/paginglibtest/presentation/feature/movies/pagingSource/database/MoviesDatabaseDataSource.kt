package com.andrew.paginglibtest.presentation.feature.movies.pagingSource.database

import android.arch.paging.PositionalDataSource
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import com.andrew.paginglibtest.utils.logger.Logger
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Andrew on 06.06.2018.
 */

class MoviesDatabaseDataSource constructor(private var repository: MoviesRepository,
                                           private var compositeDisposable: CompositeDisposable,
                                           private var logger: Logger)
    : PositionalDataSource<Movie>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Movie>) {
        compositeDisposable.add(repository.getMoviesDatabase()
                .doOnSuccess { logger.log("POSITION = " + 0 + " SIZE = " + it.size) }
                .subscribe({callback.onResult(it, 0)}, {logger.log(it)}))
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Movie>) {
        compositeDisposable.add(repository.getMoviesDatabase()
                .doOnSuccess { logger.log("POSITION = " + params.startPosition + " SIZE = " + it.size) }
                .subscribe({callback.onResult(it)}, {logger.log(it)}))
    }
}