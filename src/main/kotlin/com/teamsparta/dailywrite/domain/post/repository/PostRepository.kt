package com.teamsparta.dailywrite.domain.post.repository

import com.teamsparta.dailywrite.domain.post.model.PostEntity
import com.teamsparta.dailywrite.domain.user.model.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import java.awt.print.Pageable
import java.time.LocalDateTime

interface PostRepository : JpaRepository<PostEntity, Long>, CustomPostRepository {

    fun deleteByUpdatedAtBefore(date : LocalDateTime)

}