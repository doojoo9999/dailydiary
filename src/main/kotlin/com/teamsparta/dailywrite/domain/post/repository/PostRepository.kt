package com.teamsparta.dailywrite.domain.post.repository

import com.teamsparta.dailywrite.domain.post.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long> {
}