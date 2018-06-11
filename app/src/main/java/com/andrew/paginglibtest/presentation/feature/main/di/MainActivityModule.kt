package com.andrew.paginglibtest.presentation.feature.main.di

import android.content.Intent
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.presentation.feature.main.view.MainActivity
import com.andrew.paginglibtest.presentation.feature.moviesFromRoom.view.MoviesFromRoomActivity
import com.andrew.paginglibtest.presentation.feature.movies.view.MoviesActivity
import com.andrew.paginglibtest.presentation.navigation.MOVIES_FROM_ROOM
import com.andrew.paginglibtest.presentation.navigation.MOVIES
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
                        MOVIES -> activity.startActivity(Intent(activity, MoviesActivity::class.java))
                        MOVIES_FROM_ROOM -> activity.startActivity(Intent(activity, MoviesFromRoomActivity::class.java))
                    }
                }
            }
        }
    }
}