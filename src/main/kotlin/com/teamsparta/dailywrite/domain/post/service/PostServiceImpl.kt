package com.teamsparta.dailywrite.domain.post.service

import com.teamsparta.dailywrite.domain.global.exception.ModelNotFoundException
import com.teamsparta.dailywrite.domain.global.exception.UserNotFoundException
import com.teamsparta.dailywrite.domain.post.dto.request.CreatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.DeletePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.response.PostResponse
import com.teamsparta.dailywrite.domain.post.model.PostEntity
import com.teamsparta.dailywrite.domain.post.model.toResponse
import com.teamsparta.dailywrite.domain.post.repository.PostRepository
import com.teamsparta.dailywrite.domain.upload.service.UploadService
import com.teamsparta.dailywrite.domain.user.repository.UserRepository
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val uploadService: UploadService
) : PostService {

    override fun getPostList(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
    }

    override fun createPost(request: CreatePostRequest, file:MultipartFile?, userPrincipal: UserPrincipal): PostResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException (userPrincipal.id)
        var fileUrl: String? = null

        if (file != null) {
            val uploadResponse = uploadService.uploadFile(file, null, userPrincipal)
            fileUrl = uploadResponse.uploadedUrl
        }

        return postRepository.save(PostEntity(
            title = request.title,
            content = request.content,
            condition = request.condition,
            user = user,
            fileUrl = fileUrl
        )
        ).toResponse()
    }

    override fun updatePost(postId:Long, request: UpdatePostRequest, file:MultipartFile?, userPrincipal: UserPrincipal): PostResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException (userPrincipal.id)
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("PostId", postId)

        var fileUrl: String? = null

        if (file != null) {
            val uploadResponse = uploadService.uploadFile(file, null, userPrincipal)
            fileUrl = uploadResponse.uploadedUrl
        }

        if (post.user.id != user.id) {
            throw IllegalArgumentException ("글 작성자만 수정할 수 있습니다.")
}


        val (title, content, condition) = request

        post.title = title
        post.content = content
        post.condition = condition
        post.fileUrl = fileUrl

        return postRepository.save(post).toResponse()

    }

    override fun deletePost(postId: Long, request: DeletePostRequest, userPrincipal: UserPrincipal) : String{
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException (userPrincipal.id)
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("PostId", postId)

        if (post.user.id != user.id) {
            throw IllegalArgumentException ("글 작성자만 삭제할 수 있습니다.")
        }

        postRepository.delete(post)

        return ("삭제 완료")
    }

    override fun getPaginatedPostList(pageable: Pageable): Page<PostResponse> {
        return postRepository.findByPageable(pageable).map {it.toResponse() }
    }

    override fun searchByTitle(pageable: Pageable, title: String): Page<PostResponse> {
        return postRepository.searchByTitle(title, pageable).map {it.toResponse()}
    }

    override fun searchByNickname(pageable: Pageable, nickname: String): Page<PostResponse> {
        return postRepository.searchByNickname(nickname, pageable).map { it.toResponse() }
    }

//    override fun getPostListByNickname(nickname: String, pageable: Pageable): Page<PostResponse> {
//        val user = userRepository.findByNickname(nickname) ?: throw IllegalStateException("없는 닉네임입니다.")
//        val post = postRepository.findByUserId(user.id, pageable)
//
//        return post.map { it.toResponse() }
//    }
    @Scheduled(cron = "0 0 0 1/1 * *")
    override fun deleteOldPost() {

        val postCheck = LocalDateTime.now().minusDays(90)
        postRepository.deleteByUpdatedAtBefore(postCheck)

}


}