package com.andrew.paginglibtest.presentation.feature.movies.pagingSource

import android.arch.paging.DataSource
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import com.andrew.paginglibtest.presentation.eventBus.StateEventBus
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Andrew on 06.06.2018.
 */

@PerActivity
class MoviesDataSourceFactory @Inject constructor(private var repository: MoviesRepository,
                                                  private var compositeDisposable: CompositeDisposable,
                                                  private var stateEventBus: StateEventBus)
    : DataSource.Factory<Int, Movie>() {

    lateinit var currentSource: MoviesDataSource

    override fun create(): DataSource<Int, Movie>  {
        currentSource = MoviesDataSource(repository, compositeDisposable, stateEventBus)
        return currentSource
    }
}