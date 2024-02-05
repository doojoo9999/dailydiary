package com.teamsparta.dailywrite.domain.post.repository

import com.teamsparta.dailywrite.domain.post.model.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import java.awt.print.Pageable

interface PostRepository : JpaRepository<PostEntity, Long>, CustomPostRepository {

}