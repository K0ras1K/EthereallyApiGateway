package ru.k0ras1k.ethereally.utils

import ru.k0ras1k.ethereally.data.ServiceType
import ru.k0ras1k.ethereally.data.database.Service
import ru.k0ras1k.ethereally.data.models.ServiceData
import kotlin.random.Random

object ServiceControllerUtils {

    fun getServiceByType(type: ServiceType): ServiceData {
        val avalible_services: List<ServiceData> = Service.getByType(type)
        println("Avalible services - $avalible_services")
        val service = if (avalible_services.size > 1) avalible_services[Random.nextInt(0, avalible_services.size - 1)] else avalible_services[0]
        return service
    }

}