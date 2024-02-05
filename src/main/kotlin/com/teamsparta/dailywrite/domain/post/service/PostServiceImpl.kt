package com.teamsparta.dailywrite.domain.post.service

import com.teamsparta.dailywrite.domain.exception.ModelNotFoundException
import com.teamsparta.dailywrite.domain.post.dto.request.CreatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.DeletePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.response.PostResponse
import com.teamsparta.dailywrite.domain.post.model.PostEntity
import com.teamsparta.dailywrite.domain.post.model.toResponse
import com.teamsparta.dailywrite.domain.post.repository.PostRepository
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
) : PostService {

    override fun getPostList(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
    }

    override fun createPost(request: CreatePostRequest, userPrincipal: UserPrincipal): PostResponse {
        val userCheck = userPrincipal.id
        return postRepository.save(PostEntity(
            title = request.title,
            content = request.content,
            condition = request.condition
        )
        ).toResponse()
    }

    override fun updatePost(postId:Long, request: UpdatePostRequest, userPrincipal: UserPrincipal): PostResponse {
        val userCheck = userPrincipal.id
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("PostId", postId)

        val (title, content, condition) = request

        post.title = title
        post.content = content
        post.condition = condition

        return postRepository.save(post).toResponse()

    }

    override fun deletePost(postId: Long, request: DeletePostRequest, userPrincipal: UserPrincipal) : String{
        val userCheck = userPrincipal.id
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("PostId", postId)

        postRepository.delete(post)

        return ("삭제 완료")
    }


}