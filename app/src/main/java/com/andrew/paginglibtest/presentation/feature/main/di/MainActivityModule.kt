package com.andrew.paginglibtest.presentation.feature.main.di

import android.content.Intent
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.presentation.feature.main.view.MainActivity
import com.andrew.paginglibtest.presentation.feature.movies.di.MoviesActivityModule
import com.andrew.paginglibtest.presentation.feature.movies.pagingSource.SourceType
import com.andrew.paginglibtest.presentation.feature.moviesNetwork.view.MoviesActivity
import com.andrew.paginglibtest.presentation.navigation.MOVIES_DATABASE
import com.andrew.paginglibtest.presentation.navigation.MOVIES_NETWORK
import com.andrew.paginglibtest.presentation.navigation.MOVIES_NETWORK_AND_DATABASE
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Forward

/**
 * Created by Andrew on 10.06.2018.
 */

@Module
class MainActivityModule {

    @PerActivity
    @Provides
    fun provideNavigator(activity: MainActivity) = Navigator {
        it.forEach {
            when (it) {
                is Forward -> {
                    when(it.screenKey) {
                        MOVIES_NETWORK -> startMoviesActivity(activity, SourceType.NETWORK)
                        MOVIES_DATABASE -> startMoviesActivity(activity, SourceType.DATABASE)
                        MOVIES_NETWORK_AND_DATABASE -> startMoviesActivity(activity, SourceType.NETWORK_AND_DATABASE)
                    }
                }
            }
        }
    }

    private fun startMoviesActivity(activity: MainActivity,
                                    sourceType: SourceType) {
        activity.startActivity(Intent(activity, MoviesActivity::class.java)
                .apply { putExtra(MoviesActivityModule.EXTRA_SOURCE_TYPE, sourceType) })
    }
}