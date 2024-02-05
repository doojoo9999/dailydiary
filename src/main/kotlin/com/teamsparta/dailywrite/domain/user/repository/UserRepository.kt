package com.teamsparta.dailywrite.domain.user.repository

import com.teamsparta.dailywrite.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository <UserEntity, Long> {
}