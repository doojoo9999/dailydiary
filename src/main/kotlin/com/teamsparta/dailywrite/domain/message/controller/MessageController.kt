package com.teamsparta.dailywrite.domain.message.controller

import com.teamsparta.dailywrite.domain.message.dto.request.ReadMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.request.SendMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.response.MessageResponse
import com.teamsparta.dailywrite.domain.message.dto.response.ReadMessageResponse
import com.teamsparta.dailywrite.domain.message.service.MessageService
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/message")
class MessageController(
    private val messageService : MessageService
) {

    @PostMapping()
    fun sendMessage(
        @RequestBody request: SendMessageRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ) : ResponseEntity<MessageResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(messageService.sendMessage(request, userPrincipal))
    }

    fun readMessage(
        @RequestBody request : ReadMessageRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ) : ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(messageService.readMessage(request, userPrincipal))
    }

    fun getMessage() {

    }

    fun deleteMessage() {

    }


}