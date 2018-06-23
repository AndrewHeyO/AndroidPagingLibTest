package com.andrew.paginglibtest.presentation.feature.movies.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.andrew.paginglibtest.R
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.utils.inflate
import com.andrew.paginglibtest.utils.loadImage
import kotlinx.android.synthetic.main.item_error.view.*
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Andrew on 03.06.2018.
 */

class MoviesAdapter(diff: DiffUtil.ItemCallback<Movie>,
                    private var errorClickListener: ErrorClickListener)
    : PagedListAdapter<Movie, RecyclerView.ViewHolder>(diff) {

    private val loadingViewType = 123
    private val errorViewType = 321;

    private var isLoading = false
    private var isError = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            loadingViewType -> LoadingViewHolder(parent.inflate(R.layout.item_loading))
            errorViewType -> ErrorViewHolder(parent.inflate(R.layout.item_error))
            else -> MovieViewHolder(parent.inflate(R.layout.item_movie))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            loadingViewType -> {}
            errorViewType -> (holder as ErrorViewHolder).bind()
            else -> getItem(position)?.let { (holder as MovieViewHolder).bind(it) }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + (if (isLoading) 1 else 0) + (if (isError) 1 else 0)
    }

    override fun getItemViewType(position: Int): Int {
        if(position == itemCount - 1) {
            if(isLoading) {
                return loadingViewType
            }
            if(isError) {
                return errorViewType
            }
        }
        return super.getItemViewType(position)
    }

    fun addLoading() {
        if (isLoading) {
            return
        }
        isLoading = true
        notifyItemInserted(itemCount)
    }

    fun removeLoading() {
        if (!isLoading) {
            return
        }
        isLoading = false
        notifyItemRemoved(itemCount)
    }

    fun addError() {
        if (isError) {
            return
        }
        isError = true
        notifyItemInserted(itemCount)
    }

    fun removeError() {
        if (!isError) {
            return
        }
        isError = false
        notifyItemRemoved(itemCount)
    }

    fun setClickListener(errorClickListener: ErrorClickListener) {
        this.errorClickListener = errorClickListener
    }

    inner class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.image_poster.loadImage(movie.posterUrl)
            itemView.text_title.text = movie.title
            itemView.text_overview.text = movie.overview
        }
    }

    inner class LoadingViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    inner class ErrorViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind() {
            itemView.button_retry.setOnClickListener { errorClickListener.onRetryClicked() }
        }
    }
}