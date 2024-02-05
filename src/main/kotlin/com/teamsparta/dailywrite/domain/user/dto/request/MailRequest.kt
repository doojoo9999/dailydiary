package com.teamsparta.dailywrite.domain.user.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class MailRequest (
    @Email (message = "올바른 이메일 형식을 사용해 주세요.")
    @NotBlank (message = "{NotBlank.email}")
    val email: String,
)