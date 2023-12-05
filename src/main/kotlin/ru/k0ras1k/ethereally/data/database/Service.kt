package ru.k0ras1k.ethereally.data.database

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import ru.k0ras1k.ethereally.data.ServiceType
import ru.k0ras1k.ethereally.data.models.ServiceData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

/**
 * Service table instance
 *
 * @author Roman Kalmykov
 */

object Service : Table("services") {

    val uuid = Service.uuid("uuid")
    val type = Service.enumerationByName("type", 20, ServiceType::class)
    val ip = Service.varchar("ip", 50)
    val port = Service.varchar("port", 5)

    override val primaryKey: PrimaryKey = PrimaryKey(uuid)


    /**
     *
     * Adding service to database
     *
     * @param service_data Service object
     * @author Roman Kalmykov
     *
     */
    fun insert(service_data: ServiceData) {
        try {
            transaction {
                insert {
                    it[uuid] = service_data.uuid
                    it[type] = service_data.type
                    it[ip] = service_data.ip
                    it[port] = service_data.port
                }
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Return list of connected one type services
     *
     * @param service_type Service type
     *
     * @author Roman Kalmykov
     *
     */
    fun getByType(service_type: ServiceType): List<ServiceData> {
        return try {
            transaction {
                val services_data: MutableList<ServiceData> = mutableListOf()
                val services_respond = Service.select { Service.type.eq(service_type) }.forEach {
                    it ->
                    services_data += ServiceData(
                        uuid = it[uuid],
                        type = service_type,
                        ip = it[ip],
                        port = it[port]
                    )
                }
                services_data
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }

}