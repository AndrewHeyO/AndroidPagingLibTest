package com.andrew.paginglibtest.presentation.eventBus

import com.andrew.paginglibtest.di.PerActivity
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by Andrew on 11.06.2018.
 */

@PerActivity
class ErrorEventBus @Inject constructor() {

    var subject: PublishSubject<Throwable> = PublishSubject.create()

    fun onNext(error: Throwable) {
        subject.onNext(error)
    }
}