package com.andrew.paginglibtest.data.database

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.andrew.paginglibtest.data.model.MovieModel

/**
 * Created by Andrew on 10.06.2018.
 */

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MovieModel")
    fun getAllMovies(): DataSource.Factory<Int, MovieModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieModel>)
}