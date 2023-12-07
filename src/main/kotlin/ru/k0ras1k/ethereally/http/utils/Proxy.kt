package ru.k0ras1k.ethereally.http.utils

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import okhttp3.RequestBody.Companion.toRequestBody
import ru.k0ras1k.ethereally.http.Requester
import ru.k0ras1k.ethereally.utils.Logger
import java.io.IOException

/**
 * Object for proxying requests
 *
 * @author Roman Kalmykov
 *
 */
object Proxy {

    /**
     * Proxy request to other microservice using his URL
     *
     * @param target_url Microservice URL routing
     * @param call All default call parameters
     *
     * @author Roman Kalmykov
     */
    suspend fun proxyRequest(target_url: String, call: ApplicationCall) {

        // Добавьте логирование
        println("Tryed send request to '$target_url'")

        try {
            val targetResponse = when (call.request.httpMethod) {
                io.ktor.http.HttpMethod.Post -> Requester().post(target_url, null)
                io.ktor.http.HttpMethod.Patch -> Requester().patch(target_url, null)
                io.ktor.http.HttpMethod.Get -> Requester().get(target_url)
                io.ktor.http.HttpMethod.Delete -> Requester().delete(target_url)
                else -> throw UnsupportedOperationException("Unsupported HTTP method: ${call.request.httpMethod}")
            }

            val status = HttpStatusCode.fromValue(targetResponse.response.code)
            call.respond(
                status,
                TextContent(targetResponse.body, ContentType.Application.Json),
            )
        } catch (e: IOException) {
            // Обработка ошибок, например, если микросервис недоступен
            call.respond(HttpStatusCode.InternalServerError, e.localizedMessage)
        }
    }

}