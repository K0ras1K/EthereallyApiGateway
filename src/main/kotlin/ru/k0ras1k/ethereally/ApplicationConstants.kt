package ru.k0ras1k.ethereally

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object ApplicationConstants {

    val gson: Gson = GsonBuilder().create()

    const val SERVICE_SECRET_TOKEN: String = "kdfzjg7yzbIJHFGZIHhbfd79"

}