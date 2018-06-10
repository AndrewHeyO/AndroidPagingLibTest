package com.andrew.paginglibtest.data.network

import com.andrew.paginglibtest.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Andrew on 03.06.2018.
 */

interface MoviesApi {

    @GET("discover/movie")
    fun getMovies(@Query("page") page: Int): Single<MovieResponse>
}