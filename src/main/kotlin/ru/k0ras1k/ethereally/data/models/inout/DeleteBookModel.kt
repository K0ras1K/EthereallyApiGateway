package ru.k0ras1k.ethereally.data.models.inout

import kotlinx.serialization.Serializable

@Serializable
data class DeleteBookModel (
    val name: String
)