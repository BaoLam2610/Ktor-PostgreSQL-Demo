package com.lambao.models.domain

data class User(
    val id: Int,
    val fullName: String,
    val avatar: String,
    val email: String,
    val token: String? = null,
    val createAt: String,
)
