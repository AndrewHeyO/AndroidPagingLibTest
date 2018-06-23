package com.andrew.paginglibtest.presentation.feature.movies.pagingSource.unusedSamples

import android.arch.paging.PositionalDataSource
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Andrew on 23.06.2018
 */

@PerActivity
class MyPositionalDataSource @Inject constructor(private var repository: MoviesRepository,
                                                 private var compositeDisposable: CompositeDisposable)
    : PositionalDataSource<Movie>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Movie>) {
        compositeDisposable.add(repository.getMoviesWithOffset(params.requestedStartPosition, params.requestedLoadSize)
                .subscribe({ callback.onResult(it, params.requestedStartPosition) }, {  }))
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Movie>) {
        compositeDisposable.add(repository.getMoviesWithOffset(params.startPosition, params.loadSize)
                .subscribe({ callback.onResult(it) }, {  }))
    }
}