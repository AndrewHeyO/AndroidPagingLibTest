package com.andrew.paginglibtest.data.mapper

import com.andrew.paginglibtest.data.model.MovieModel
import com.andrew.paginglibtest.data.network.BASE_IMAGE_ROUTE
import com.andrew.paginglibtest.domain.entity.Movie
import io.reactivex.functions.Function
import javax.inject.Inject

/**
 * Created by Andrew on 03.06.2018.
 */

class MovieMapper @Inject constructor() : Function<MovieModel, Movie> {
    override fun apply(t: MovieModel): Movie =
            Movie(t.id, t.title, if (t.posterPath != null) BASE_IMAGE_ROUTE + t.posterPath else "", t.overview, t.date)

    fun mapList(list: List<MovieModel>): List<Movie> = list.map { apply(it) }
}