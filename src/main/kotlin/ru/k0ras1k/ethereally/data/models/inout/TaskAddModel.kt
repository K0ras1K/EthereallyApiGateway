package ru.k0ras1k.ethereally.data.models.inout

import kotlinx.serialization.Serializable

@Serializable
data class TaskAddModel (
    val name: String,
    val about: String,
    val cost: Int,
    val max_join: Int,
    val expires: Long,
    val type: String
)