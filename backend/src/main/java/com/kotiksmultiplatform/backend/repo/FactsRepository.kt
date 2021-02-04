package com.kotiksmultiplatform.backend.repo

import com.kotiksmultiplatform.backend.dbQuery
import com.kotiksmultiplatform.backend.entities.Fact
import com.kotiksmultiplatform.backend.tables.Facts
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class FactsRepository {

    suspend fun getFacts(): List<Fact> = dbQuery {
        Facts.selectAll().map { toFact(it) }
    }

    private fun toFact(row: ResultRow) = Fact(
        id = row[Facts.id],
        text = row[Facts.text]
    )

}