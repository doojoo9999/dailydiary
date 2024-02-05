package com.teamsparta.dailywrite.domain.user.dto.request

import com.teamsparta.dailywrite.domain.user.model.UserRole
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequest (
    @Email (message = "Email.email")
    @NotBlank (message = "NotBlank.email")
    val email : String,
    @NotBlank (message = "NotBlank.password")
    val password : String,
    @NotBlank (message = "확인 비밀번호를 입력해 주세요.")
    val checkPassword : String,
    @NotBlank (message = "NotBlank.nickname")
    @Min(value = 3, message = "최소 3자 이상의 닉네임을 설정해 주세요.")
    val nickname : String,
    @NotBlank (message = "NotBlank.authCode")
    @Size(min=6, max=6, message = "인증 코드는 6글자여야 합니다.")
    val authCode : String
)