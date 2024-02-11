package com.teamsparta.dailywrite.domain.upload.service

import com.teamsparta.dailywrite.domain.upload.dto.request.UploadRequest
import com.teamsparta.dailywrite.domain.upload.dto.response.UploadResponse
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import org.springframework.web.multipart.MultipartFile

interface UploadService {

    fun uploadFile (file: MultipartFile, uploadRequest: UploadRequest?, userPrincipal: UserPrincipal) : UploadResponse

}