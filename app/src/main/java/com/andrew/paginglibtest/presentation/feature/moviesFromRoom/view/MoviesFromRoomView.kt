package com.andrew.paginglibtest.presentation.feature.moviesFromRoom.view

import android.arch.paging.PagedList
import com.andrew.paginglibtest.domain.entity.Movie
import com.arellomobile.mvp.MvpView

/**
 * Created by Andrew on 03.06.2018.
 */

interface MoviesFromRoomView : MvpView {

    fun submitMovies(movies: PagedList<Movie>)
}