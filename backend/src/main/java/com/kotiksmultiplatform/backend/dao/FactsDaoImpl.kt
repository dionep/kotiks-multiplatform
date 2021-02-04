package com.kotiksmultiplatform.backend.dao

import com.kotiksmultiplatform.backend.tables.Facts
import org.h2.engine.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.transaction

class FactsDaoImpl(val db: Database) : FactsDao {
    override fun init() = transaction(db) {
        SchemaUtils.create(Facts)
    }

    override fun createFact(text: String) = transaction(db) {
        Facts.insert {
            it[Facts.text] = text
        }
        Unit
    }

}