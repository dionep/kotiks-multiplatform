package com.dionep.kotiksmultiplatform.features.changes

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.subject.publish.PublishSubject

internal class Changes {

    private val factAddedSubject = PublishSubject<Any>()

    val factAddedObservable: Observable<Any>
        get() = factAddedSubject.observeOn(mainScheduler)

    fun onFactAdded() {
        factAddedSubject.onNext(Any())
    }

}