package com.andrew.paginglibtest.presentation.feature.moviesFromRoom.view

import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.andrew.paginglibtest.R
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.presentation.feature.moviesFromRoom.adapter.MoviesFromRoomAdapter
import com.andrew.paginglibtest.presentation.feature.moviesFromRoom.presenter.MoviesFromRoomPresenter
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject


/**
 * Created by Andrew on 03.06.2018.
 */

class MoviesFromRoomActivity : MvpAppCompatActivity(), MoviesFromRoomView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MoviesFromRoomPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @Inject
    lateinit var adapter: MoviesFromRoomAdapter

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
            adapter = this@MoviesFromRoomActivity.adapter
            layoutManager = this@MoviesFromRoomActivity.layoutManager
        }
        layout_refresh.setOnRefreshListener {
            presenter.refresh()
            layout_refresh.isRefreshing = false
        }
    }

    override fun submitMovies(movies: PagedList<Movie>) {
        adapter.submitList(movies)
    }
}
