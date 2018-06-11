package com.andrew.paginglibtest.di.module

import android.content.Context
import com.andrew.paginglibtest.PagingApplication
import com.andrew.paginglibtest.utils.logger.Logger
import com.andrew.paginglibtest.utils.logger.LoggerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Andrew on 03.06.2018.
 */

@Module
abstract class AppModule {

    @Module
    companion object {
        @Singleton
        @Provides
        @JvmStatic
        fun provideApplicationContext(app: PagingApplication): Context = app
    }

    @Singleton
    @Binds
    abstract fun provideLogger(logger: LoggerImpl): Logger
}