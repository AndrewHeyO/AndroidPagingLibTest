package com.andrew.paginglibtest.presentation.feature.movies.presenter

import android.arch.paging.RxPagedListBuilder
import com.andrew.paginglibtest.presentation.feature.movies.pagingSource.MoviesDataSourceFactory
import com.andrew.paginglibtest.presentation.feature.movies.view.MoviesView
import com.andrew.paginglibtest.utils.logger.Logger
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Andrew on 03.06.2018.
 */

@InjectViewState
class MoviesPresenter
@Inject constructor(private val sourceFactory: MoviesDataSourceFactory,
                    private val compositeDisposable: CompositeDisposable,
                    private val logger: Logger) : MvpPresenter<MoviesView>() {

    override fun onFirstViewAttach() {
        loadMoviesAndObserveErrors()
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe()
    }

    fun reload() {
        sourceFactory.source.reload()
    }

    fun refresh() {
        unsubscribe()
        loadMoviesAndObserveErrors()
    }

    private fun loadMoviesAndObserveErrors() {
        compositeDisposable.add(RxPagedListBuilder(sourceFactory, 20)
                .buildObservable()
                .subscribe({ viewState.submitMovies(it) }, { logger.log(it) }))

        compositeDisposable.add(sourceFactory.source.observeState()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.handleState(it.type)
                    it.error?.let { logger.log(it) }
                }, { logger.log(it) }))
    }

    private fun unsubscribe() {
        compositeDisposable.clear()
    }
}