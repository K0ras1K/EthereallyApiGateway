package ru.k0ras1k.ethereally.data.models.inout

import kotlinx.serialization.Serializable
import ru.k0ras1k.ethereally.data.ServiceType

/**
 * Input JSON model for serializing money update request params
 *
 * @param email User email
 * @param money_count New user money count
 *
 * @author Roman Kalmykov
 */

@Serializable
data class MoneyUpdateModel (
    val email: String,
    val money_count: Int
)