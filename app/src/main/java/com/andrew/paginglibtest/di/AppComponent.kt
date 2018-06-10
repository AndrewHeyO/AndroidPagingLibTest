package com.andrew.paginglibtest.di

import com.andrew.paginglibtest.PagingApplication
import com.andrew.paginglibtest.di.module.AppModule
import com.andrew.paginglibtest.di.module.BuilderModule
import com.andrew.paginglibtest.di.module.NetworkModule
import com.andrew.paginglibtest.di.module.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by Andrew on 03.06.2018.
 */

@Singleton
@Component(modules = [AppModule::class,
    BuilderModule::class,
    NetworkModule::class,
    RepositoryModule::class])
interface AppComponent : AndroidInjector<PagingApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PagingApplication>()
}