package ru.k0ras1k.ethereally.data.models.inout

import kotlinx.serialization.Serializable

@Serializable
data class CreateBookModel (
    val name: String,
    val link: String
)