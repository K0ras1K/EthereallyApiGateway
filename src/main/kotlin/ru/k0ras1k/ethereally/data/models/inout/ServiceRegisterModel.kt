package ru.k0ras1k.ethereally.data.models.inout

import kotlinx.serialization.Serializable
import ru.k0ras1k.ethereally.data.ServiceType
import java.util.*


/**
 * Input JSON model for registering service request
 *
 *  @param uuid Service UUID
 *  @param type Service type
 *  @param ip Service ip
 *  @param port Service port
 *  @author Roman Kalmykov
 */
@Serializable
data class ServiceRegisterModel (
    val type: ServiceType,
    val ip: String,
    val port: String
)