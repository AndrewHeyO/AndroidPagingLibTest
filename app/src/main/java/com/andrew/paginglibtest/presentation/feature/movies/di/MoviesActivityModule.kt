package com.andrew.paginglibtest.presentation.feature.movies.di

import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import com.andrew.paginglibtest.presentation.feature.movies.adapter.MoviesAdapter
import com.andrew.paginglibtest.presentation.feature.movies.pagingSource.MoviesDataSourceFactory
import com.andrew.paginglibtest.presentation.feature.movies.pagingSource.SourceType
import com.andrew.paginglibtest.presentation.feature.moviesNetwork.view.MoviesActivity
import com.andrew.paginglibtest.utils.logger.Logger
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Andrew on 07.06.2018.
 */

@Module
class MoviesActivityModule {

    companion object {
        val EXTRA_SOURCE_TYPE = "EXTRA_SOURCE_TYPE"
    }

    @PerActivity
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @PerActivity
    @Provides
    fun provideMoviesAdapter(callback: DiffUtil.ItemCallback<Movie>) = MoviesAdapter(callback)

    @PerActivity
    @Provides
    fun provideMoviesDiffUtilCallback() = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }

    @PerActivity
    @Provides
    fun provideLayoutManager(activity: MoviesActivity): RecyclerView.LayoutManager = LinearLayoutManager(activity)

    @PerActivity
    @Provides
    fun provideMoviesDataSourceFactory(repository: MoviesRepository,
                                       compositeDisposable: CompositeDisposable,
                                       logger: Logger,
                                       activity: MoviesActivity) =
            MoviesDataSourceFactory(repository, compositeDisposable, logger,
                    activity.intent.getSerializableExtra(EXTRA_SOURCE_TYPE) as SourceType)
}