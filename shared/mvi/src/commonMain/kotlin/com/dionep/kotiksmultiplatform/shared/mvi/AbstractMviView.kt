package com.dionep.kotiksmultiplatform.shared.mvi

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.subject.publish.PublishSubject

abstract class AbstractMviView<in Model : Any, Event : Any> : MviView<Model, Event> {

    private val subject = PublishSubject<Event>()
//    override val events: Observable<Event> = subject

//    open fun accept(event: Event) {
//        subject.onNext(event)
//    }
}
