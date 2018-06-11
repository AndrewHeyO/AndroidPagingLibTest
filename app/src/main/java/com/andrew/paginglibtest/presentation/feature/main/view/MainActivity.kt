package com.andrew.paginglibtest.presentation.feature.main.view

import android.os.Bundle
import com.andrew.paginglibtest.R
import com.andrew.paginglibtest.presentation.feature.base.view.BaseActivity
import com.andrew.paginglibtest.presentation.feature.main.presenter.MainPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import javax.inject.Inject

/**
 * Created by Andrew on 10.06.2018.
 */

class MainActivity : BaseActivity(), MainView {

    @Inject
    override lateinit var navigator: Navigator

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_movies.setOnClickListener { presenter.navigateToMovies() }
        button_movies_from_room.setOnClickListener { presenter.navigateToMoviesFromRoom() }
    }
}
