package com.andrew.paginglibtest.di.module

import com.andrew.paginglibtest.data.repository.MoviesRepositoryImpl
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Andrew on 07.06.2018.
 */

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideMoviesRepository(repository: MoviesRepositoryImpl): MoviesRepository
}