package com.teamsparta.dailywrite.domain.upload.repository

import com.teamsparta.dailywrite.domain.upload.model.UploadEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UploadRepository : JpaRepository<UploadEntity, Long> {
}