package com.andrew.paginglibtest.presentation.feature.movies.pagingSource

import android.arch.paging.DataSource
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Andrew on 06.06.2018.
 */

@PerActivity
class MoviesDataSourceFactory @Inject constructor(private var repository: MoviesRepository,
                                                  private var compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie> = MoviesDataSource(repository, compositeDisposable)
}