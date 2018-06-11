package com.andrew.paginglibtest.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.andrew.paginglibtest.data.database.Database
import com.andrew.paginglibtest.data.database.MoviesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Andrew on 10.06.2018.
 */

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): Database =
            Room.databaseBuilder(context, Database::class.java, Database.NAME)
                    .build()

    @Singleton
    @Provides
    fun provideMoviesDao(database: Database) : MoviesDao = database.moviesDao()

}