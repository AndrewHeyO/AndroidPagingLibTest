package com.andrew.paginglibtest.di.module

import android.content.Context
import com.andrew.paginglibtest.PagingApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Andrew on 03.06.2018.
 */

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(app: PagingApplication): Context = app
}