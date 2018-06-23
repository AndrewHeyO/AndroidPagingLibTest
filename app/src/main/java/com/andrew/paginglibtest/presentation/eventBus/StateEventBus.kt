package com.andrew.paginglibtest.presentation.eventBus

import com.andrew.paginglibtest.di.PerActivity
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by Andrew on 23.06.2018
 */

@PerActivity
class StateEventBus @Inject constructor() {

    val subject: PublishSubject<State> = PublishSubject.create()

    fun onNext(state: State) {
        subject.onNext(state)
    }
}