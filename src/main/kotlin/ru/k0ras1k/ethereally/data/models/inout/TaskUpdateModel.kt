package ru.k0ras1k.ethereally.data.models.inout

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class TaskUpdateModel (
    val uuid: String,
    val name: String,
    val about: String,
    val cost: Int,
    val status: String,
    val max_join: Int,
    val expires: Long,
    val type: String
)