package com.andrew.paginglibtest.presentation.feature.moviesFromRoom.presenter

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.andrew.paginglibtest.data.database.MoviesDao
import com.andrew.paginglibtest.data.mapper.MovieMapper
import com.andrew.paginglibtest.presentation.feature.moviesFromRoom.view.MoviesFromRoomView
import com.andrew.paginglibtest.utils.logger.Logger
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Andrew on 03.06.2018.
 */

@InjectViewState
class MoviesFromRoomPresenter
@Inject constructor(private var mapper: MovieMapper,
                    private var dao: MoviesDao,
                    private var compositeDisposable: CompositeDisposable,
                    private var logger: Logger) : MvpPresenter<MoviesFromRoomView>() {

    private var config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(20)
            .setInitialLoadSizeHint(40)
            .setPrefetchDistance(10)
            .build()

    override fun onFirstViewAttach() {
        loadMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe()
    }

    private fun loadMovies() {
        compositeDisposable.add(RxPagedListBuilder(dao.getAllMovies()
                .map { mapper.apply(it) }, config)
                .buildObservable()
                .subscribe({ viewState.submitMovies(it) }, { logger.log(it) }))
    }

    private fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun refresh() {
        unsubscribe()
        loadMovies()
    }
}