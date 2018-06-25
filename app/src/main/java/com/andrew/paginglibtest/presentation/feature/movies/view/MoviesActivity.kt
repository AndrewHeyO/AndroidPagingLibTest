package com.andrew.paginglibtest.presentation.feature.movies.view

import android.app.SearchManager
import android.arch.paging.PagedList
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.widget.SearchView
import com.andrew.paginglibtest.R
import com.andrew.paginglibtest.domain.entity.Movie
import com.andrew.paginglibtest.presentation.feature.movies.adapter.ErrorClickListener
import com.andrew.paginglibtest.presentation.feature.movies.adapter.MoviesAdapter
import com.andrew.paginglibtest.presentation.feature.movies.presenter.MoviesPresenter
import com.andrew.paginglibtest.presentation.viewModels.State
import com.andrew.paginglibtest.utils.logger.Logger
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding2.widget.RxSearchView
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_movies.*
import java.util.concurrent.TimeUnit
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

    @Inject
    lateinit var logger: Logger

    private var disposable: Disposable? = null

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        disposable = RxSearchView.queryTextChanges(searchView).skipInitialValue()
                .debounce(100, TimeUnit.MILLISECONDS)
                .subscribe({ presenter.searchMovies(it.toString()) }, { logger.log(it) })

        return true
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
                if (adapter.itemCount > 0) {
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
