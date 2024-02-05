package com.teamsparta.dailywrite.domain.post.dto.request

data class CreatePostRequest (
    val title : String,
    val content : String,
    val condition : com.teamsparta.dailywrite.domain.post.model.Condition,
)