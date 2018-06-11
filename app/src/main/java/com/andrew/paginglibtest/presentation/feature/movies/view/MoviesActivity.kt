package com.andrew.paginglibtest.presentation.feature.movies.view

import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.andrew.paginglibtest.R
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.presentation.feature.movies.adapter.MoviesAdapter
import com.andrew.paginglibtest.presentation.feature.movies.presenter.MoviesPresenter
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : MvpAppCompatActivity(), MoviesView {

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
        recycler_movies.apply {
            adapter = this@MoviesActivity.adapter
            layoutManager = this@MoviesActivity.layoutManager
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun addMovies(movies: PagedList<Movie>) {
        adapter.submitList(movies)
    }
}
