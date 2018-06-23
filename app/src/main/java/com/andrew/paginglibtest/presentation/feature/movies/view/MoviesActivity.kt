package com.andrew.paginglibtest.presentation.feature.movies.view

import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.andrew.paginglibtest.R
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.presentation.eventBus.State
import com.andrew.paginglibtest.presentation.feature.movies.adapter.ErrorClickListener
import com.andrew.paginglibtest.presentation.feature.movies.adapter.MoviesAdapter
import com.andrew.paginglibtest.presentation.feature.movies.presenter.MoviesPresenter
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

/**
 * Created by Andrew on 03.06.2018.
 */

class MoviesActivity : MvpAppCompatActivity(), MoviesView, ErrorClickListener {

    @Inject
    @InjectPresenter
    lateinit var presenter: MoviesPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @Inject
    lateinit var adapter: MoviesAdapter

    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        recycler_movies.apply {
            adapter = this@MoviesActivity.adapter
            layoutManager = this@MoviesActivity.layoutManager
        }
        layout_refresh.setOnRefreshListener {
            presenter.refresh()
        }
    }

    override fun submitMovies(movies: PagedList<Movie>) {
        adapter.submitList(movies)
    }

    override fun handleState(type: State.Type) {
        when (type) {
            State.Type.NONE -> {
                layout_refresh.isRefreshing = false
                adapter.removeLoading()
                adapter.removeError()
            }
            State.Type.LOADING -> {
                adapter.removeError()
                if(adapter.itemCount > 0) {
                    adapter.addLoading()
                } else {
                    layout_refresh.isRefreshing = true
                }
            }
            State.Type.ERROR -> {
                layout_refresh.isRefreshing = false
                adapter.removeLoading()
                adapter.addError()
            }
        }
    }

    override fun onRetryClicked() {
        presenter.reload()
    }
}
