package com.dionep.kotiksmultiplatform.shared.mvi

import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.Observable

interface Feature<in Intent : Any, out State : Any> : Consumer<Intent>, Observable<State>, Disposable {

    val state: State
}
