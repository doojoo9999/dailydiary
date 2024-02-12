package com.teamsparta.dailywrite.domain.message.controller

import com.teamsparta.dailywrite.domain.message.dto.request.SendMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.response.MessageResponse
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/message")
class MessageController {

    fun sendMessage(
        @RequestBody sendMessageRequest: SendMessageRequest
    ) : ResponseEntity<MessageResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(messageService.sendMessage(sendMessageRequest))
    }

    fun getMessage() {

    }

    fun deleteMessage() {

    }


}