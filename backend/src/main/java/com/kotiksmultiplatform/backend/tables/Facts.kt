package com.kotiksmultiplatform.backend.tables

import org.jetbrains.exposed.sql.Table

object Facts : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val text = varchar("text", 2048)
}