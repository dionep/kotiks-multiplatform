package com.dionep.kotiksmultiplatform.shared.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateFactRequest(
    val text: String
)