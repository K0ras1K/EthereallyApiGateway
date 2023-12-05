package ru.k0ras1k.ethereally

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import ru.k0ras1k.ethereally.data.database.Service
import ru.k0ras1k.ethereally.utils.Logger

object Initialization {

    val TABLES_LIST: List<Any> = listOf(
        Service
    )

    fun initialize() {
        for (table in TABLES_LIST) {
            transaction {
                SchemaUtils.create(table as Table)
            }

        }

        Logger.log("Started initizlization")
    }

}
