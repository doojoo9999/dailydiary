package com.teamsparta.dailywrite.domain.message.repository

import com.teamsparta.dailywrite.domain.message.model.MessageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<MessageEntity, Long> {
}