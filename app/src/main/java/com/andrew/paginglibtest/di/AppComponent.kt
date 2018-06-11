package com.andrew.paginglibtest.di

import com.andrew.paginglibtest.PagingApplication
import com.andrew.paginglibtest.di.module.*
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
    RepositoryModule::class,
    DatabaseModule::class,
    NavigationModule::class])
interface AppComponent : AndroidInjector<PagingApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PagingApplication>()
}