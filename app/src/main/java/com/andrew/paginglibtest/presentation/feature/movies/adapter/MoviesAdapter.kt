package com.andrew.paginglibtest.presentation.feature.movies.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.andrew.paginglibtest.R
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.utils.inflate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Andrew on 03.06.2018.
 */

class MoviesAdapter(diff: DiffUtil.ItemCallback<Movie>) : PagedListAdapter<Movie, MoviesAdapter.MovieViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
            MovieViewHolder(parent.inflate(R.layout.item_movie))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                    .load(movie.posterUrl)
                    .apply(RequestOptions().circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(itemView.image_poster)
            itemView.text_title.text = movie.title
            itemView.text_overview.text = movie.overview
        }
    }
}