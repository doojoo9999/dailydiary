package com.teamsparta.dailywrite.domain.post.dto.request

import com.teamsparta.dailywrite.domain.post.model.Condition
import jakarta.validation.constraints.NotBlank
import org.springframework.web.multipart.MultipartFile

data class UpdatePostRequest (
    @NotBlank(message = "NotBlank.title")
    val title : String,
    @NotBlank (message = "NotBlank.content")
    val content : String,
    @NotBlank (message = "NotBlank.condition")
    val condition : Condition,
)