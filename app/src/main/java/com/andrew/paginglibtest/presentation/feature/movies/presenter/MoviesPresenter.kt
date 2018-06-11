package com.andrew.paginglibtest.presentation.feature.movies.presenter

import android.arch.paging.RxPagedListBuilder
import com.andrew.paginglibtest.presentation.feature.movies.pagingSource.MoviesDataSourceFactory
import com.andrew.paginglibtest.presentation.feature.movies.view.MoviesView
import com.andrew.paginglibtest.utils.logger.Logger
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Andrew on 03.06.2018.
 */

@InjectViewState
class MoviesPresenter
@Inject constructor(private var moviesDataSourceFactory: MoviesDataSourceFactory,
                    private var compositeDisposable: CompositeDisposable,
                    private var logger: Logger) : MvpPresenter<MoviesView>() {

    val PAGE_SIZE = 20

    fun getMovies() {
        compositeDisposable.add(RxPagedListBuilder(moviesDataSourceFactory, PAGE_SIZE)
                .buildObservable()
                .subscribe({ viewState.addMovies(it) }, { logger.log(it) }))
    }

    fun unsubscribe() {
        compositeDisposable.clear()
    }
}