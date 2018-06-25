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

    @GET("search/movie")
    fun searchMovies(@Query("page") page: Int, @Query("query") query: String): Single<MovieResponse>

    @GET
    fun getMovies(@Query("offset") offset: Int, @Query("limit") limit: Int): Single<MovieResponse>

    @GET
    fun getMovies(@Query("last_title") id: String?): Single<MovieResponse>
}