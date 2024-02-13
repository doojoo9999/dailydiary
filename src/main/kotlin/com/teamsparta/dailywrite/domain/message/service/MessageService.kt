package com.teamsparta.dailywrite.domain.message.service

import com.teamsparta.dailywrite.domain.message.dto.request.DeleteMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.request.ReadMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.request.SendMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.response.MessageResponse
import com.teamsparta.dailywrite.domain.message.dto.response.ReadMessageResponse
import com.teamsparta.dailywrite.infra.security.UserPrincipal

interface MessageService {

    fun sendMessage(request : SendMessageRequest, userPrincipal: UserPrincipal) : MessageResponse

    fun readMessage(request : ReadMessageRequest, userPrincipal: UserPrincipal)

    fun deleteMessage (request: DeleteMessageRequest, userPrincipal: UserPrincipal)
}