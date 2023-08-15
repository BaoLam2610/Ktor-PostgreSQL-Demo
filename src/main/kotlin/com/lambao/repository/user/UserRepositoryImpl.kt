package com.lambao.repository.user

import com.lambao.models.params.LoginParams
import com.lambao.models.params.RegisterParams
import com.lambao.models.response.BaseResponse
import com.lambao.security.JwtConfig
import com.lambao.services.user.UserService

class UserRepositoryImpl(
    private val service: UserService
) : UserRepository {
    override suspend fun register(params: RegisterParams): BaseResponse<Any> {
        if (isEmailExists(params.email)) {
            return BaseResponse.Error(message = "Email đã tồn tại!")
        }
        val user = service.register(params)
        if (user != null) {
            val token = JwtConfig.instance.createAccessToken(user.id)
            user.token = token
            return BaseResponse.Success(
                message = "Đăng ký thành công", user
            )
        }
        return BaseResponse.Error()
    }

    override suspend fun login(params: LoginParams): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExists(email: String?) = service.findUserByEmail(email) != null
}