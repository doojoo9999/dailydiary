package com.teamsparta.dailywrite.domain.user.repository

import com.teamsparta.dailywrite.domain.user.model.MailEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MailRepository : JpaRepository <MailEntity,Long> {
    fun findAllByEmail (email: String) : List<MailEntity>?

    fun deleteByEmail (email: String)
}