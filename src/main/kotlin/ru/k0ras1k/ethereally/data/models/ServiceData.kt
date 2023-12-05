package ru.k0ras1k.ethereally.data.models

import ru.k0ras1k.ethereally.data.ServiceType
import java.util.UUID

/**
 * Default service model
 *
 * @author Roman Kalmykov
 *
 * @param uuid Service UUID
 * @param type Service type
 * @param ip Service ip
 * @param port Service port
 *
 */

data class ServiceData (
    val uuid: UUID,
    val type: ServiceType,
    val ip: String,
    val port: String
)