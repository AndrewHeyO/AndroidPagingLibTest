package com.andrew.paginglibtest.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Andrew on 03.06.2018.
 */

data class MovieResponse(@SerializedName("results") var list: List<MovieModel>)

data class MovieModel(@SerializedName("id") var id: Long,
                      @SerializedName("title") var title: String,
                      @SerializedName("poster_path") var posterPath: String,
                      @SerializedName("overview") var overview: String,
                      @SerializedName("release_date") var date: String)