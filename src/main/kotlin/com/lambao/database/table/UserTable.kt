package com.lambao.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : Table("users") {
    val id = integer("id").autoIncrement()
    val fullName = varchar("full_name", 256)
    val avatar = text("avatar")
    val email = varchar("email", 256)
    val password = text("password")
    val createAt = datetime("create_at").clientDefault { LocalDateTime.now() }

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}
