package com.teamsparta.dailywrite.domain.upload.service

import com.teamsparta.dailywrite.domain.global.exception.ModelNotFoundException
import com.teamsparta.dailywrite.domain.global.exception.UserNotFoundException
import com.teamsparta.dailywrite.domain.post.repository.PostRepository
import com.teamsparta.dailywrite.domain.upload.dto.request.UploadRequest
import com.teamsparta.dailywrite.domain.upload.dto.response.UploadResponse
import com.teamsparta.dailywrite.domain.upload.model.UploadEntity
import com.teamsparta.dailywrite.domain.upload.repository.UploadRepository
import com.teamsparta.dailywrite.domain.user.repository.UserRepository
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.runBlocking
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class UploadServiceImpl(
    val userRepository: UserRepository,
    val supabase: SupabaseClient,
    val uploadRepository: UploadRepository,
    val postRepository: PostRepository
) : UploadService {
    override fun uploadFile(
        file: MultipartFile,
        uploadRequest: UploadRequest?,
        userPrincipal: UserPrincipal
    ): UploadResponse {

        val BUCKET_NAME = "upload"
        val filePath = UUID.randomUUID().toString() + '.' + (file.originalFilename!!.split('.')[1])

        runBlocking {
            supabase.storage.from(BUCKET_NAME).upload(filePath, file.bytes, upsert = false)
        }

        val fileUrl = supabase.storage.from("$BUCKET_NAME").publicUrl(filePath)
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException (userPrincipal.id)
//        val post = postRepository.findByIdOrNull(uploadRequest?.postId) ?: throw ModelNotFoundException ("Post id", uploadRequest.postId)

        val post = if (uploadRequest != null) {
            postRepository.findByIdOrNull(uploadRequest.postId)
        } else {null}

        val upload = UploadEntity (
            user = user,
            fileUrl = fileUrl,
            post = post
        )

        uploadRepository.save(upload)

        return UploadResponse(
            uploadedUrl = fileUrl,
            message = "업로드 성공"
        )
    }

}