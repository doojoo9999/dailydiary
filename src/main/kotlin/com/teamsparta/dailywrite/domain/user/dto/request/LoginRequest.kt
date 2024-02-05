package com.teamsparta.dailywrite.domain.user.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class LoginRequest(
    @Email (message = "{Email.email}")
    @NotBlank (message = "NotBlank.email")
    val email : String,

    @NotBlank (message = "NotBlank.password")
    val password : String
)