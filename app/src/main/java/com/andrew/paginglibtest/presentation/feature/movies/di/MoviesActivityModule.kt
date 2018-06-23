package com.andrew.paginglibtest.presentation.feature.movies.di

import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.presentation.feature.movies.adapter.MoviesAdapter
import com.andrew.paginglibtest.presentation.feature.movies.view.MoviesActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Andrew on 07.06.2018.
 */

@Module
class MoviesActivityModule {

    @PerActivity
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @PerActivity
    @Provides
    fun provideMoviesAdapter(callback: DiffUtil.ItemCallback<Movie>, activity: MoviesActivity) = MoviesAdapter(callback, activity)

    @PerActivity
    @Provides
    fun provideMoviesDiffUtilCallback() = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }

    @PerActivity
    @Provides
    fun provideLayoutManager(activity: MoviesActivity): RecyclerView.LayoutManager = LinearLayoutManager(activity)
}