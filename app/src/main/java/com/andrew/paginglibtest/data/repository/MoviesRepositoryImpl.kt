package com.andrew.paginglibtest.data.repository

import com.andrew.paginglibtest.data.database.MoviesDao
import com.andrew.paginglibtest.data.mapper.MovieMapper
import com.andrew.paginglibtest.data.model.MovieResponse
import com.andrew.paginglibtest.data.network.MoviesApi
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.domain.repository.MoviesRepository
import io.reactivex.Single
import io.reactivex.SingleTransformer
import javax.inject.Inject

/**
 * Created by Andrew on 03.06.2018.
 */

class MoviesRepositoryImpl
@Inject constructor(private val api: MoviesApi,
                    private val dao: MoviesDao,
                    private val movieMapper: MovieMapper) : MoviesRepository {

    override fun getMovies(page: Int): Single<List<Movie>> =
            api.getMovies(page)
                    .compose(mapAndCacheData())

    override fun searchMovies(page: Int, query: String): Single<List<Movie>> =
            api.searchMovies(page, query)
                    .compose(mapAndCacheData())

    override fun getMoviesWithOffset(offset: Int, limit: Int): Single<List<Movie>> =
            api.getMovies(offset, limit)
                    .compose(mapAndCacheData())

    override fun getMoviesByLastName(name: String?): Single<List<Movie>> =
            api.getMovies(name)
                    .compose(mapAndCacheData())

    private fun mapAndCacheData(): SingleTransformer<MovieResponse, List<Movie>> {
        return SingleTransformer {
            it.map { it.list }
                    .doOnSuccess { dao.insertMovies(it) }
                    .map { movieMapper.mapList(it) }
        }
    }
}