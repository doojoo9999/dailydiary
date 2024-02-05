package com.teamsparta.dailywrite.domain.post.model

import com.teamsparta.dailywrite.domain.post.dto.response.PostResponse
import jakarta.persistence.*

@Entity
@Table(name = "posts")
class PostEntity(

    @Column(name = "title")
    var title: String,
    @Column(name = "content")
    var content: String,

//    @ManyToOne (fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    val user: UserEntity
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0
}

fun PostEntity.toResponse() : PostResponse {
    return PostResponse(
        title = title,
        content = content
    )
}