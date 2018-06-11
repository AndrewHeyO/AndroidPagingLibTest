package com.andrew.paginglibtest.domain.repository

import com.andrew.paginglibtest.domain.entity.Movie
import io.reactivex.Single

/**
 * Created by Andrew on 03.06.2018.
 */

interface MoviesRepository {

    fun getMoviesNetwork(page: Int): Single<List<Movie>>

    fun getMoviesDatabase(): Single<List<Movie>>

    //fun getMoviesNetworkAndDatabase(page: Int): Single<List<Movie>>
}