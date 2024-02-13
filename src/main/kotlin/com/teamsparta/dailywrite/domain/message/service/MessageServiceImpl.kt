package com.teamsparta.dailywrite.domain.message.service

import com.teamsparta.dailywrite.domain.global.exception.ModelNotFoundException
import com.teamsparta.dailywrite.domain.global.exception.UserNotFoundException
import com.teamsparta.dailywrite.domain.message.dto.request.DeleteMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.request.ReadMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.request.SendMessageRequest
import com.teamsparta.dailywrite.domain.message.dto.response.MessageResponse
import com.teamsparta.dailywrite.domain.message.dto.response.ReadMessageResponse
import com.teamsparta.dailywrite.domain.message.model.MessageEntity
import com.teamsparta.dailywrite.domain.message.repository.MessageRepository
import com.teamsparta.dailywrite.domain.user.repository.UserRepository
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    private val messageRepository: MessageRepository,
    private val userRepository : UserRepository
) : MessageService {
    override fun sendMessage(request: SendMessageRequest, userPrincipal: UserPrincipal): MessageResponse {
        val sendUser = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException (userPrincipal.id)
        val receiveUser = userRepository.findByIdOrNull(request.receiveUserId) ?: throw UserNotFoundException (request.receiveUserId)

         messageRepository.save(MessageEntity(
             title = request.title,
             content = request.content,
             sendUserId = sendUser,
             receiveUserId = receiveUser
         ))

        return MessageResponse(message = "발송 완료")
    }

    override fun readMessage(request: ReadMessageRequest, userPrincipal: UserPrincipal) {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException (userPrincipal.id)
        val message = messageRepository.findByIdOrNull(request.id) ?: throw ModelNotFoundException ("Message Id", request.id)

        message.readAt

        messageRepository.save(message)

    }

    override fun deleteMessage(request: DeleteMessageRequest, userPrincipal: UserPrincipal) {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException (userPrincipal.id)
        val message = messageRepository.findByIdOrNull(request.id) ?: throw ModelNotFoundException ("Message Id", request.id)

        messageRepository.delete(message)

    }


}