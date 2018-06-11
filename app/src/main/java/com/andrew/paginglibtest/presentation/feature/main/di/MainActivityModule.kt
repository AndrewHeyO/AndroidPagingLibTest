package com.andrew.paginglibtest.presentation.feature.main.di

import android.content.Intent
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.presentation.feature.main.view.MainActivity
import com.andrew.paginglibtest.presentation.feature.movies.view.MoviesActivity
import com.andrew.paginglibtest.presentation.navigation.MOVIES_BY_PAGE
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Forward

@Module
class MainActivityModule {

    @PerActivity
    @Provides
    fun provideNavigator(activity: MainActivity) = Navigator {
        it.forEach {
            when (it) {
                is Forward -> {
                    if (it.screenKey == MOVIES_BY_PAGE) {
                        activity.startActivity(Intent(activity, MoviesActivity::class.java))
                    }
                }
            }
        }
    }
}