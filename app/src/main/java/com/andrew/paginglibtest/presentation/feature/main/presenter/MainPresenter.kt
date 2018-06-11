package com.andrew.paginglibtest.presentation.feature.main.presenter

import com.andrew.paginglibtest.presentation.feature.main.view.MainView
import com.andrew.paginglibtest.presentation.navigation.MOVIES_DATABASE
import com.andrew.paginglibtest.presentation.navigation.MOVIES_NETWORK
import com.andrew.paginglibtest.presentation.navigation.MOVIES_NETWORK_AND_DATABASE
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by Andrew on 10.06.2018.
 */

class MainPresenter @Inject constructor(private var router: Router): MvpPresenter<MainView>() {

    fun navigateToNetworkMovies() {
        router.navigateTo(MOVIES_NETWORK)
    }

    fun navigateToDatabaseMovies() {
        router.navigateTo(MOVIES_DATABASE)
    }

    fun navigateToNetworkAndDatabaseMovies() {
        router.navigateTo(MOVIES_NETWORK_AND_DATABASE)
    }

}