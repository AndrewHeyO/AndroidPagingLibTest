package com.andrew.paginglibtest.di.module

import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.presentation.feature.main.di.MainActivityModule
import com.andrew.paginglibtest.presentation.feature.main.view.MainActivity
import com.andrew.paginglibtest.presentation.feature.movies.view.MoviesActivity
import com.andrew.paginglibtest.presentation.feature.movies.di.MoviesActivityModule
import com.andrew.paginglibtest.presentation.feature.moviesFromRoom.di.MoviesFromRoomActivityModule
import com.andrew.paginglibtest.presentation.feature.moviesFromRoom.view.MoviesFromRoomActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Andrew on 07.06.2018.
 */

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class BuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MoviesFromRoomActivityModule::class])
    abstract fun moviesFromRoomActivityInjector(): MoviesFromRoomActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MoviesActivityModule::class])
    abstract fun moviesActivityInjector(): MoviesActivity
}