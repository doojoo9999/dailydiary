package com.teamsparta.dailywrite.domain.user.dto.request

import com.teamsparta.dailywrite.domain.user.model.UserRole

data class SignUpRequest (
    val email : String,
    val password : String,
    val nickname : String,
    val authCode : String,
    val role : UserRole,
)