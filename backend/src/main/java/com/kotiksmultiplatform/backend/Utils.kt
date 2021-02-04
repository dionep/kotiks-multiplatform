package com.kotiksmultiplatform.backend

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.experimental.transaction

suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }