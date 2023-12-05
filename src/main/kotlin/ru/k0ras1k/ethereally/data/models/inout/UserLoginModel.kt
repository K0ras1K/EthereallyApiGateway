package ru.k0ras1k.ethereally.data.models.inout

import kotlinx.serialization.Serializable


/**
 * Input JSON model for serializing login request params
 *
 *  @param email User email
 *  @param password User password
 *
 *  @author Roman Kalmykov
 */
@Serializable
data class UserLoginModel (
    val email: String,
    val password: String
)
