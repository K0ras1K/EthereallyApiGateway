package ru.k0ras1k.ethereally.data.models.inout

import kotlinx.serialization.Serializable


/**
 * Input JSON model for serializing register request params
 *
 *  @param email User email
 *  @param phone_number User phone number
 *  @param first_name User first name
 *  @param last_name User last name
 *  @param password User password
 *
 *  @author Roman Kalmykov
 */
@Serializable
data class UserRegisterModel (
    val email: String,
    val phone_number: String,
    val first_name: String,
    val last_name: String,
    val password: String,
)