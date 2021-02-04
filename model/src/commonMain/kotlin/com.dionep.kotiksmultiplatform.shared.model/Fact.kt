package com.dionep.kotiksmultiplatform.shared.model

import kotlinx.serialization.*

@Serializable
data class Fact(
    val id: String,
    val text: String,
)