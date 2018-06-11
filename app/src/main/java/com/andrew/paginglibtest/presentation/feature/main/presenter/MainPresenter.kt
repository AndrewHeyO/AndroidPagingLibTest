package com.andrew.paginglibtest.presentation.feature.main.presenter

import com.andrew.paginglibtest.presentation.feature.main.view.MainView
import com.andrew.paginglibtest.presentation.navigation.MOVIES
import com.andrew.paginglibtest.presentation.navigation.MOVIES_FROM_ROOM
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by Andrew on 10.06.2018.
 */

class MainPresenter @Inject constructor(private var router: Router): MvpPresenter<MainView>() {

    fun navigateToMovies() {
        router.navigateTo(MOVIES)
    }

    fun navigateToMoviesFromRoom() {
        router.navigateTo(MOVIES_FROM_ROOM)
    }
}