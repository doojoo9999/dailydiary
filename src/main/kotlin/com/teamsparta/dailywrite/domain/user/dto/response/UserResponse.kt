package com.teamsparta.dailywrite.domain.user.dto.response

import com.teamsparta.dailywrite.domain.user.model.UserRole

data class UserResponse(
    val id: Long,
    val email: String,
    val role: UserRole
)
