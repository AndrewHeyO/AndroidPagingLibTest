package com.andrew.paginglibtest.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.andrew.paginglibtest.data.model.MovieModel
import io.reactivex.Single


@Dao
interface MoviesDao {

    @Query("SELECT * FROM MovieModel")
    fun getAll(): Single<List<MovieModel>>

    @Insert
    fun insert(movie: MovieModel)

    @Delete
    fun delete(movie: MovieModel)
}