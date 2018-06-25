package com.andrew.paginglibtest.presentation.feature.movies.view

import android.arch.paging.PagedList
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.presentation.viewModels.State
import com.arellomobile.mvp.MvpView

/**
 * Created by Andrew on 03.06.2018.
 */

interface MoviesView : MvpView {

    fun submitMovies(movies: PagedList<Movie>)

    fun handleState(type: State.Type)
}