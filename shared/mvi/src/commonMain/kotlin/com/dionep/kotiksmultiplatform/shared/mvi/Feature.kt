package com.dionep.kotiksmultiplatform.shared.mvi

import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.ObservableObserver
import com.badoo.reaktive.subject.behavior.BehaviorSubject
import com.badoo.reaktive.utils.ensureNeverFrozen

abstract class Feature<in Intent : Any, out State : Any, in Effect: Any>(
    initialState: State,
    private val actor: Actor<State, Intent, Effect>,
    private val reducer: Reducer<State, Effect>
) :
    Consumer<Intent>,
    Observable<State>,
    DisposableScope by DisposableScope()
{

    init {
        ensureNeverFrozen()
    }

    private val subject = BehaviorSubject(initialState)
    val state: State get() = subject.value

    override fun onNext(value: Intent) {
        actor(subject.value, value).subscribeScoped(isThreadLocal = true, onNext = ::handleEffect)
    }

    private fun handleEffect(effect: Effect) {
        subject.onNext(reducer(subject.value, effect))
    }

    override fun subscribe(observer: ObservableObserver<State>) {
        subject.subscribe(observer)
    }

}
