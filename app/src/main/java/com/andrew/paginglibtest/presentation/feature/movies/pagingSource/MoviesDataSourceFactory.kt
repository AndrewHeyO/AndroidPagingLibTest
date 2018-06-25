package com.andrew.paginglibtest.presentation.feature.movies.pagingSource

import android.arch.paging.DataSource
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.domain.entity.Movie
import javax.inject.Inject

/**
 * Created by Andrew on 06.06.2018.
 */

@PerActivity
class MoviesDataSourceFactory @Inject constructor(val source: MoviesDataSource)
    : DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie>  {
        return source
    }
}