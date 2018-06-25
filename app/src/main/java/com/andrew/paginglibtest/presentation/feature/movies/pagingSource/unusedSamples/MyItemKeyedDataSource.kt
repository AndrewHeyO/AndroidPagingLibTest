package com.andrew.paginglibtest.presentation.feature.movies.pagingSource.unusedSamples

import android.arch.paging.ItemKeyedDataSource
import com.andrew.paginglibtest.di.PerActivity
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Andrew on 23.06.2018
 */

@PerActivity
class MyItemKeyedDataSource @Inject constructor(private var repository: MoviesRepository,
                                                private var compositeDisposable: CompositeDisposable)
    : ItemKeyedDataSource<String, Movie>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<Movie>) {
        compositeDisposable.add(repository.getMoviesByLastName(params.requestedInitialKey)
                .subscribe({ callback.onResult(it) }, {  }))
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Movie>) {
        compositeDisposable.add(repository.getMoviesByLastName(params.key)
                .subscribe({ callback.onResult(it) }, {  }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Movie>) {
        compositeDisposable.add(repository.getMoviesByLastName(params.key)
                .subscribe({ callback.onResult(it) }, {  }))
    }

    override fun getKey(item: Movie) = item.title
}