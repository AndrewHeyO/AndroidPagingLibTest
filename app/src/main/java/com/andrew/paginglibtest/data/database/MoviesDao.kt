package com.andrew.paginglibtest.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.andrew.paginglibtest.data.model.MovieModel
import io.reactivex.Single

/**
 * Created by Andrew on 10.06.2018.
 */

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MovieModel")
    fun getAllMovies(): Single<List<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieModel>)
}