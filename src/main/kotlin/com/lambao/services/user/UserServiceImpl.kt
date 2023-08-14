package com.lambao.services.user

import com.lambao.extensions.query
import com.lambao.models.domain.User
import com.lambao.database.table.UserTable
import com.lambao.models.params.RegisterParams
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserService {
    override suspend fun register(params: RegisterParams): User? {
        var statement : InsertStatement<Number>? = null
        query {
            statement = UserTable.insert {
                it[email] = params.email ?: ""
                it[password] = params.password ?: ""
                it[fullName] = params.fullName ?: ""
                it[avatar] = params.avatar ?: ""
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String?): User? {
        val user = query {
            UserTable.select { UserTable.email.eq(email ?: return@query null) }
                .map { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    private fun rowToUser(row: ResultRow?): User? {
        return if(row == null) null
        else User(
            id = row[UserTable.id],
            fullName = row[UserTable.fullName],
            avatar = row[UserTable.avatar],
            email = row[UserTable.email],
            createAt = row[UserTable.createAt].toString(),
        )
    }
}