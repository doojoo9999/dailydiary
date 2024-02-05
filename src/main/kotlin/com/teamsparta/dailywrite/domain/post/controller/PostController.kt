package com.teamsparta.dailywrite.domain.post.controller

import com.teamsparta.dailywrite.domain.post.dto.request.CreatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.DeletePostRequest
import com.teamsparta.dailywrite.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.dailywrite.domain.post.dto.response.PostResponse
import com.teamsparta.dailywrite.domain.post.service.PostService
import com.teamsparta.dailywrite.infra.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
    fun getPostList(
        @PageableDefault(
            size = 5,
            sort = ["title"]
        ) pageable : Pageable,
    ) : ResponseEntity<Page<PostResponse>> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPaginatedPostList(pageable))

    }

    @PostMapping()
    fun createPost(
        @Valid
        @RequestBody request: CreatePostRequest,
        @AuthenticationPrincipal userPrincipal : UserPrincipal
    ):ResponseEntity<PostResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(request, userPrincipal))
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @Valid
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
        @Valid
        @PathVariable postId: Long,
        @RequestBody request : DeletePostRequest,
        @AuthenticationPrincipal userPrincipal : UserPrincipal
    ) : String {
        return postService.deletePost(postId, request, userPrincipal)
    }

    @GetMapping("/title")
    fun searchByTitle(
        @PageableDefault(
            size = 5,
            sort = ["created_at"]
        ) pageable: Pageable,
        @RequestParam title : String
    ) : ResponseEntity<Page<PostResponse>> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.searchByTitle(pageable, title))
    }

    @GetMapping("/nickname")
    fun searchByNickname(
        @PageableDefault(
            size = 5,
            sort = ["created_at"]
        ) pageable: Pageable,
        @RequestParam nickname : String
    ) : ResponseEntity<Page<PostResponse>> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.searchByNickname(pageable, nickname))

    }

}