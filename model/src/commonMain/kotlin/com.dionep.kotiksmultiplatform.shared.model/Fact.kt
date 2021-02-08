package com.dionep.kotiksmultiplatform.shared.model

import kotlinx.serialization.*

@Serializable
data class Fact(
    val id: Int,
    val text: String,
)