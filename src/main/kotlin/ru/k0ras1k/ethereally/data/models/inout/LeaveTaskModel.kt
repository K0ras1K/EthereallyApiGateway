package ru.k0ras1k.ethereally.data.models.inout

import kotlinx.serialization.Serializable

@Serializable
data class LeaveTaskModel (
    val email: String,
    val task_uuid: String
)