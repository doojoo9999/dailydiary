package com.teamsparta.dailywrite.domain.post.repository

import com.teamsparta.dailywrite.domain.post.model.PostEntity
import com.teamsparta.dailywrite.domain.user.model.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import java.awt.print.Pageable

interface PostRepository : JpaRepository<PostEntity, Long>, CustomPostRepository {

    fun findByUser (pageable: org.springframework.data.domain.Pageable, user: UserEntity) : Page<PostEntity>
}