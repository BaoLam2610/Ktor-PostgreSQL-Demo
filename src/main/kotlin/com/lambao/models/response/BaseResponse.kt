package com.lambao.models.response

import io.ktor.http.*

sealed class BaseResponse<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK
) {
    data class Success<T>(
        val message: String? = null,
        val data: T? = null
    ): BaseResponse<T>()

    data class Error<T>(
        val message: String? = null,
        val exception: T? = null
    ): BaseResponse<T>()
}
