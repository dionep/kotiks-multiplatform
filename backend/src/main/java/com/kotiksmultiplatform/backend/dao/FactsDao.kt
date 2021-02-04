package com.kotiksmultiplatform.backend.dao

import io.ktor.utils.io.core.*

interface FactsDao: Closeable {
    fun init()
    fun createFact(text: String)
}