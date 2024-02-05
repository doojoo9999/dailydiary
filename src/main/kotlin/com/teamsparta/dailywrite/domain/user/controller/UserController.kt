package com.teamsparta.dailywrite.domain.user.controller

import com.teamsparta.dailywrite.domain.user.dto.request.LoginRequest
import com.teamsparta.dailywrite.domain.user.dto.request.MailRequest
import com.teamsparta.dailywrite.domain.user.dto.request.SignUpRequest
import com.teamsparta.dailywrite.domain.user.dto.response.*
import com.teamsparta.dailywrite.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService : UserService
) {
    @PostMapping("/email")
    fun email(
        @Valid
        @RequestBody mailRequest : MailRequest
    ) : ResponseEntity <MailResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.sendMail(mailRequest))
    }

    @PostMapping("/signup")
    fun signUp(
        @Valid
        @RequestBody signUpRequest : SignUpRequest
    ) : ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(signUpRequest))
    }

    @PostMapping("/login")
    fun login(
        @Valid
        @RequestBody loginRequest: LoginRequest
    ) : ResponseEntity<LoginResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginRequest))
    }

    @PostMapping("/nickname")
    fun checkNickname(
        @Valid
        @RequestParam nickname : String
    ) : ResponseEntity<CheckNicknameResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.checkNickname(nickname))
    }

}