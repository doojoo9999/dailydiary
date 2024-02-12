package com.teamsparta.dailywrite.domain.message.dto.request

data class SendMessageRequest (
    val title : String,
    val description : String,
    val receiveUserId: Long,
)