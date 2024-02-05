package com.teamsparta.dailywrite.domain.post.repository

import com.teamsparta.dailywrite.domain.post.model.PostEntity
import org.springframework.data.domain.Page
import java.time.LocalDateTime

interface CustomPostRepository {

    fun findByPageable (pageable : org.springframework.data.domain.Pageable) : Page<PostEntity>

    fun searchByTitle (title : String, pageable : org.springframework.data.domain.Pageable) : Page<PostEntity>

    fun searchByNickname (nickname : String, pageable : org.springframework.data.domain.Pageable) : Page<PostEntity>

    fun searchByDate (date : LocalDateTime ,pageable : org.springframework.data.domain.Pageable) : Page<PostEntity>

}