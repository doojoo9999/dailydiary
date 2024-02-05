package com.teamsparta.dailywrite.domain.post.dto.request

import jakarta.validation.constraints.NotBlank

data class CreatePostRequest (
    @NotBlank (message = "NotBlank.title")
    val title : String,
    @NotBlank (message = "NotBlank.content")
    val content : String,
    @NotBlank (message = "NotBlank.condition")
    val condition : com.teamsparta.dailywrite.domain.post.model.Condition,
)