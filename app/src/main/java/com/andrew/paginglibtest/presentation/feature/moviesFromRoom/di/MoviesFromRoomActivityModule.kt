package com.andrew.paginglibtest.presentation.feature.moviesFromRoom.di

import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.presentation.feature.moviesFromRoom.adapter.MoviesFromRoomAdapter
import com.andrew.paginglibtest.presentation.feature.moviesFromRoom.view.MoviesFromRoomActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Andrew on 07.06.2018.
 */

@Module
class MoviesFromRoomActivityModule {

    @PerActivity
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @PerActivity
    @Provides
    fun provideMoviesAdapter(callback: DiffUtil.ItemCallback<Movie>) = MoviesFromRoomAdapter(callback)

    @PerActivity
    @Provides
    fun provideMoviesDiffUtilCallback() = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }

    @PerActivity
    @Provides
    fun provideLayoutManager(activity: MoviesFromRoomActivity): RecyclerView.LayoutManager = LinearLayoutManager(activity)
}