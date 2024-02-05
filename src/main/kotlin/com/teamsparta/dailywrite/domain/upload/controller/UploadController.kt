package com.teamsparta.dailywrite.domain.upload.controller

import com.teamsparta.dailywrite.domain.upload.dto.request.UploadRequest
import com.teamsparta.dailywrite.domain.upload.dto.response.UploadResponse
import com.teamsparta.dailywrite.domain.upload.service.UploadService
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/upload")
class UploadController(
    private val uploadService : UploadService
) {

    @PostMapping()
    fun fileUpload(
        @RequestPart ("file") file: MultipartFile,
        @RequestPart ("uploadRequest") uploadRequest: UploadRequest,
        @AuthenticationPrincipal userPrincipal : UserPrincipal
    ) : ResponseEntity <UploadResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(uploadService.uploadFile(file, uploadRequest, userPrincipal))
    }

}