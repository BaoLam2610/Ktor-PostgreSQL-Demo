package com.lambao.models.params

import kotlinx.serialization.Serializable

@Serializable
data class RegisterParams(
    val email: String?,
    val password: String?,
    val fullName: String?,
    val avatar: String?,
)