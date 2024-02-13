package com.teamsparta.dailywrite.domain.message.dto.request

data class SendMessageRequest (
    val title : String,
    val content : String,
    val receiveUserId: Long,
)