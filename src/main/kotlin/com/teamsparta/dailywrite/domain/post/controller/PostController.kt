package com.teamsparta.dailywrite.domain.post.controller

import com.teamsparta.dailywrite.domain.post.dto.request.CreatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.DeletePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.response.PostResponse
import com.teamsparta.dailywrite.domain.post.service.PostService
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posts")
class PostController (
    private val postService : PostService
){

    @GetMapping()
    fun getPostList(){}

    @PostMapping()
    fun createPost(
        @RequestBody request: CreatePostRequest,
        @AuthenticationPrincipal userPrincipal : UserPrincipal
    ):ResponseEntity<PostResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(request, userPrincipal))
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable postId : Long,
        @RequestBody request : UpdatePostRequest,
        @AuthenticationPrincipal userPrincipal : UserPrincipal
    ) : ResponseEntity<PostResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(postId, request, userPrincipal))
    }

    @DeleteMapping("/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
        @RequestBody request : DeletePostRequest,
        @AuthenticationPrincipal userPrincipal : UserPrincipal
    ) : String {
        return postService.deletePost(postId, request, userPrincipal)
    }


}