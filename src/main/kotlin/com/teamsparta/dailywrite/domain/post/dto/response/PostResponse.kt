package com.teamsparta.dailywrite.domain.post.dto.response

import com.teamsparta.dailywrite.domain.post.model.Condition

data class PostResponse (
    val title: String,
    val content: String,
    val condition : Condition,
    val fileUrl : String?
)