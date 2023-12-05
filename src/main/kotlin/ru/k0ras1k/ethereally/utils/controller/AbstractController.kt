package ru.k0ras1k.ethereally.utils.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

abstract class AbstractController(val call: ApplicationCall) {

    val principal = call.principal<JWTPrincipal>()
    val username = principal!!.payload.getClaim("email").asString()

}