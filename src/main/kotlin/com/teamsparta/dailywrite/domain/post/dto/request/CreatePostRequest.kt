package com.teamsparta.dailywrite.domain.post.dto.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

data class CreatePostRequest (
    @NotBlank (message = "NotBlank.title")
    @Max(value = 500, message = "제목은 500자를 초과할 수 없습니다.")
    val title : String,

    @NotBlank (message = "NotBlank.content")
    @Max(value = 5000, message = "내용은 5,000자를 초과할 수 없습니다.")
    val content : String,

    @NotBlank (message = "NotBlank.condition")
    val condition : com.teamsparta.dailywrite.domain.post.model.Condition,
)