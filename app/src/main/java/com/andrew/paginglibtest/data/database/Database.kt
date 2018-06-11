package com.andrew.paginglibtest.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.andrew.paginglibtest.data.model.MovieModel

@Database(entities = [MovieModel::class], version = 1)
abstract class Database() : RoomDatabase() {

    companion object {
        val NAME = "movie_db"
    }

    abstract fun moviesDao() : MoviesDao
}