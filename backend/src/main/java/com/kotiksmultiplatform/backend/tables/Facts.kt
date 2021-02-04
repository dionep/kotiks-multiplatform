package com.kotiksmultiplatform.backend.tables

import org.h2.table.Table

object Facts : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val text = varchar("text", 2048)
}