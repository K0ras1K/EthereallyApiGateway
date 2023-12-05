package ru.k0ras1k.ethereally.utils

import io.ktor.util.logging.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Logger {

    val LOGGER: Logger = LoggerFactory.getLogger("ETHEREALLY")

    fun log(text: String) {
        LOGGER.info(text)
    }

    fun debug(text: String) {
        LOGGER.debug(text)
    }

    fun warn(text: String) {
        LOGGER.warn(text)
    }

    fun error(text: String) {
        LOGGER.error(text)
    }

    fun error(exception: Exception) {
        LOGGER.error(exception)
    }
}