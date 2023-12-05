package ru.k0ras1k.ethereally.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.coroutines.runBlocking
import ru.k0ras1k.ethereally.data.database.Service
import ru.k0ras1k.ethereally.data.models.ServiceData
import ru.k0ras1k.ethereally.data.models.inout.ServiceRegisterModel
import java.util.*

/**
 * This class methods handle all service requests
 *
 * @param call Call from client
 *
 * @author Roman Kalmykov
 *
 */

class ServiceController(val call: ApplicationCall) {

    /**
     * Handle register service request
     *
     * @author Roman Kalmykov
     *
     */
    suspend fun register() {
        runBlocking {
            val recieve = call.receive<ServiceRegisterModel>()
            val uuid = UUID.randomUUID()
            Service.insert(
                ServiceData(
                    uuid = uuid,
                    type = recieve.type,
                    ip = recieve.ip,
                    port = recieve.port
                )
            )
            call.respond(HttpStatusCode.OK, "{ \"uuid\": \"$uuid\" }")
        }
    }

}