package com.andrew.paginglibtest.di.module

import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.presentation.feature.movies.di.MoviesActivityModule
import com.andrew.paginglibtest.presentation.feature.movies.view.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Andrew on 07.06.2018.
 */
@Module(includes = [AndroidSupportInjectionModule::class])
abstract class BuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MoviesActivityModule::class])
    abstract fun moviesActivityInjector(): MoviesActivity
}