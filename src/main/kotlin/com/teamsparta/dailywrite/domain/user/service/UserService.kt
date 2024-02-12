package com.teamsparta.dailywrite.domain.user.service

import com.teamsparta.dailywrite.domain.user.dto.request.LoginRequest
import com.teamsparta.dailywrite.domain.user.dto.request.MailRequest
import com.teamsparta.dailywrite.domain.user.dto.request.SignUpRequest
import com.teamsparta.dailywrite.domain.user.dto.response.CheckNicknameResponse
import com.teamsparta.dailywrite.domain.user.dto.response.LoginResponse
import com.teamsparta.dailywrite.domain.user.dto.response.MailResponse
import com.teamsparta.dailywrite.domain.user.dto.response.UserResponse

interface UserService {

    fun signUp(request: SignUpRequest) : UserResponse

    fun login(request: LoginRequest) : LoginResponse

    fun sendMail(request : MailRequest) : MailResponse

    fun checkNickname(nickname : String) : CheckNicknameResponse

    fun deleteMail()
}