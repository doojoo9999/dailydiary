package com.teamsparta.dailywrite.domain.user.dto.request

data class LoginRequest(
    val email : String,
    val password : String
)