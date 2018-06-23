package com.andrew.paginglibtest.data.repository

import com.andrew.paginglibtest.data.database.MoviesDao
import com.andrew.paginglibtest.data.mapper.MovieMapper
import com.andrew.paginglibtest.data.network.MoviesApi
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Andrew on 03.06.2018.
 */

class MoviesRepositoryImpl
@Inject constructor(private var api: MoviesApi,
                    private var dao: MoviesDao,
                    private var movieMapper: MovieMapper) : MoviesRepository {

    override fun getMovies(page: Int): Single<List<Movie>> = api.getMovies(page)
            .map { it.list }
            .doOnSuccess { dao.insertMovies(it) }
            .map { movieMapper.mapList(it) }

    override fun getMoviesWithOffset(offset: Int, limit: Int): Single<List<Movie>> = Single.fromCallable { ArrayList<Movie>() }

    override fun getMoviesByName(name: String?): Single<List<Movie>> = Single.fromCallable { ArrayList<Movie>() }
}