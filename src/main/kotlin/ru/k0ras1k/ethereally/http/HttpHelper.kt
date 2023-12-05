package ru.k0ras1k.ethereally.http

import okhttp3.Response
import ru.k0ras1k.ethereally.ApplicationConstants
import java.io.IOException


/**
 * Sun class for requester
 * @param response Http response on request
 * @author Roman Kalmykov
 */
class HttpHelper(val response: Response) {
    val body: String = response.body!!.string()

    inline fun <reified T>  getOrThrow(): T {
        if (!response.isSuccessful)
            throw IOException(response.toString())

        return ApplicationConstants.gson.fromJson(body, T::class.java)
    }
}