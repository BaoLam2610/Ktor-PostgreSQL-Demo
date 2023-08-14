package com.lambao.services.user

import com.lambao.models.domain.User
import com.lambao.models.params.RegisterParams

interface UserService {
    suspend fun register(params: RegisterParams): User?
    suspend fun findUserByEmail(email: String?): User?
}