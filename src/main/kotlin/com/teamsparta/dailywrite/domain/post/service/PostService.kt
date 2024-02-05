package com.teamsparta.dailywrite.domain.post.service

import com.teamsparta.dailywrite.domain.post.dto.request.CreatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.DeletePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.response.PostResponse
import com.teamsparta.dailywrite.infra.security.UserPrincipal

interface PostService {

    fun getPostList() : List<PostResponse>
    fun createPost(request : CreatePostRequest, userPrincipal: UserPrincipal) : PostResponse

    fun updatePost(postId: Long, request : UpdatePostRequest, userPrincipal: UserPrincipal) : PostResponse

    fun deletePost(postId: Long, request : DeletePostRequest, userPrincipal: UserPrincipal) : String

}