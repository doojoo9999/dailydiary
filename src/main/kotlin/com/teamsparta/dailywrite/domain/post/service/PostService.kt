package com.teamsparta.dailywrite.domain.post.service

import com.teamsparta.dailywrite.domain.post.dto.request.CreatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.DeletePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.response.PostResponse
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostService {

    fun getPostList() : List<PostResponse>
    fun createPost(request : CreatePostRequest, userPrincipal: UserPrincipal) : PostResponse

    fun updatePost(postId: Long, request : UpdatePostRequest, userPrincipal: UserPrincipal) : PostResponse

    fun deletePost(postId: Long, request : DeletePostRequest, userPrincipal: UserPrincipal) : String

    fun getPaginatedPostList (pageable: Pageable) : Page<PostResponse>

    fun searchByTitle (pageable: Pageable, title: String) : Page<PostResponse>

    fun searchByNickname (pageable: Pageable, nickname : String) : Page<PostResponse>

//    fun getPostListByNickname (nickname: String, pageable: Pageable) : Page<PostResponse>
}