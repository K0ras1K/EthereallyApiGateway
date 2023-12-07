package ru.k0ras1k.ethereally.routing

import io.ktor.content.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.k0ras1k.ethereally.controller.ServiceController
import ru.k0ras1k.ethereally.data.ServiceType
import ru.k0ras1k.ethereally.data.models.inout.*
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
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/users/register/${receive.email}/${receive.phone_number}/${receive.first_name}/${receive.last_name}/${receive.password}/${receive.telegram}/${receive.about}/${receive.role}", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/users/update") {
            val receive = call.receive<UserUpdateModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.USERS_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/users/update/${receive.email}/${receive.about}", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/users/login") {
            val receive = call.receive<UserLoginModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.USERS_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/users/login/${receive.email}/${receive.password}", call)
        }

        get("$ROUTING_PREFIX$VERSION_PREFIX/users/about/{email}") {
            val service = ServiceControllerUtils.getServiceByType(ServiceType.USERS_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/users/about/${call.parameters["email"]!!}", call)
        }

        get("$ROUTING_PREFIX$VERSION_PREFIX/users/get/{token}") {
            val service = ServiceControllerUtils.getServiceByType(ServiceType.USERS_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/users/get/${call.parameters["token"]!!}", call)
        }

        patch("$ROUTING_PREFIX$VERSION_PREFIX/money/update") {
            val recieve = call.receive<MoneyUpdateModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.MONEY_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/money/update/${recieve.email}/${recieve.money_count}", call)
        }

        get("$ROUTING_PREFIX$VERSION_PREFIX/money/count/{email}") {
            val email = call.parameters["email"]!!
            val service = ServiceControllerUtils.getServiceByType(ServiceType.MONEY_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/money/get/$email", call)
        }

        get("$ROUTING_PREFIX$VERSION_PREFIX/money/top/get") {
            val service = ServiceControllerUtils.getServiceByType(ServiceType.MONEY_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/money/top/get", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/task/add") {
            val recieve = call.receive<TaskAddModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.TASK_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/task/add/${recieve.name}/${recieve.about}/${recieve.cost}/${recieve.max_join}/${recieve.expires}/${recieve.type}", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/task/remove") {
            val recieve = call.receive<TaskRemoveModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.TASK_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/task/delete/${recieve.name}", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/task/join") {
            val receive = call.receive<JoinTaskModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.TASK_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/task/join/${receive.email}/${receive.task_uuid}", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/task/leave") {
            val receive = call.receive<LeaveTaskModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.TASK_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/task/leave/${receive.email}/${receive.task_uuid}", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/task/validate") {
            val receive = call.receive<TaskValidateModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.TASK_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/task/validate/${receive.name}", call)
        }

        get("$ROUTING_PREFIX$VERSION_PREFIX/task/get/{type}") {
            val type = call.parameters["type"]!!
            val service = ServiceControllerUtils.getServiceByType(ServiceType.TASK_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/task/get/$type", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/library/create") {
            val receive = call.receive<CreateBookModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.LIBRARY_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/library/add/${receive.name}/${receive.link}", call)
        }

        post("$ROUTING_PREFIX$VERSION_PREFIX/library/delete") {
            val receive = call.receive<DeleteBookModel>()
            val service = ServiceControllerUtils.getServiceByType(ServiceType.LIBRARY_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/library/remove/${receive.name}", call)
        }

        get("$ROUTING_PREFIX$VERSION_PREFIX/library/load") {
            val service = ServiceControllerUtils.getServiceByType(ServiceType.LIBRARY_SERVICE)
            Proxy.proxyRequest("http://${service.ip}:${service.port}/public/v1/library/load", call)
        }


    }


}
