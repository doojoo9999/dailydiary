package com.teamsparta.dailywrite.domain.message.service

import com.teamsparta.dailywrite.domain.global.exception.UserNotFoundException
import com.teamsparta.dailywrite.domain.message.dto.request.SendMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.response.MessageResponse
import com.teamsparta.dailywrite.domain.message.repository.MessageRepository
import com.teamsparta.dailywrite.domain.user.repository.UserRepository
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    private val messageRepository: MessageRepository,
    private val userRepository : UserRepository
) : MessageService{
    override fun sendMessage(request: SendMessageRequest, userPrincipal: UserPrincipal): MessageResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException (userPrincipal.id)

    }

}