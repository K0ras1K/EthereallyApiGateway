package ru.k0ras1k.ethereally.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.conigureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
