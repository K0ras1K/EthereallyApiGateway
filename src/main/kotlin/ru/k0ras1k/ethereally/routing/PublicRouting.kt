package ru.k0ras1k.ethereally.routing

import io.ktor.content.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.k0ras1k.ethereally.controller.ServiceController
import ru.k0ras1k.ethereally.data.ServiceType
import ru.k0ras1k.ethereally.data.models.inout.MoneyUpdateModel
import ru.k0ras1k.ethereally.data.models.inout.UserLoginModel
import ru.k0ras1k.ethereally.data.models.inout.UserRegisterModel
import ru.k0ras1k.ethereally.http.Requester
import ru.k0ras1k.ethereally.http.utils.Proxy
import ru.k0ras1k.ethereally.utils.ServiceControllerUtils
import java.io.IOException


fun Application.configurePublicRouting() {
    val ROUTING_PREFIX: String = "/public"
    val VERSION_PREFIX: String = "/v1"

    routing {
        // UNAUTHENTICATED
        get("/v") {
            call.respond(HttpStatusCode.OK, "Ethereally-api version 0.1")
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/services/register") {
            ServiceController(call).register()
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/users/register") {
            val receive = call.receive<UserRegisterModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.USERS_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/users/register/${receive.email}/${receive.phone_number}/${receive.first_name}/${receive.last_name}/${receive.password}", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/users/login") {
            val receive = call.receive<UserLoginModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.USERS_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/users/login/${receive.email}/${receive.password}", call)
        }

        patch("$ROUTING_PREFIX$VERSION_PREFIX/money/update") {
            val recieve = call.receive<MoneyUpdateModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.MONEY_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/money/update/${recieve.email}/${recieve.money_count}", call)
        }




    }


}
