package com.teamsparta.dailywrite.domain.post.dto.request

import com.teamsparta.dailywrite.domain.post.model.Condition

data class UpdatePostRequest (
    val title : String,
    val content : String,
    val condition : Condition
)