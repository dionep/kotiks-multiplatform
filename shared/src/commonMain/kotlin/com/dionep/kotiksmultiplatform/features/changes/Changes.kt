package com.dionep.kotiksmultiplatform.features.changes

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.observable.threadLocal
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.subject.publish.PublishSubject
import com.badoo.reaktive.utils.ensureNeverFrozen

internal class Changes {

    private val factsChangedSubject = PublishSubject<Any>().ensureNeverFrozen()

    val factChangedObservable: Observable<Any>
        get() = factsChangedSubject
            .threadLocal()
            .observeOn(mainScheduler)

    fun onFactsChanged() {
        factsChangedSubject.onNext(Any())
    }

}