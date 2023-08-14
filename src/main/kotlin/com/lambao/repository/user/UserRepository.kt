package com.lambao.repository.user

import com.lambao.models.params.LoginParams
import com.lambao.models.params.RegisterParams
import com.lambao.models.response.BaseResponse

interface UserRepository {
    suspend fun register(params: RegisterParams): BaseResponse<Any>
    suspend fun login(params: LoginParams): BaseResponse<Any>
}