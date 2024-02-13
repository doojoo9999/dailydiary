package com.teamsparta.dailywrite.domain.message.dto.response

data class ReadMessageResponse(
    val id : Long,
    val sendUser : String,
    val title : String,
    val content : String,
)
