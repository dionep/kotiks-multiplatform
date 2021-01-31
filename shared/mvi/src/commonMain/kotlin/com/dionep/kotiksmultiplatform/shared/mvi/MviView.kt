package com.dionep.kotiksmultiplatform.shared.mvi

interface MviView<in Model : Any, out Event : Any> {

    fun render(model: Model)

}
