package com.teamsparta.dailywrite.domain.upload.model

import com.teamsparta.dailywrite.domain.post.model.PostEntity
import com.teamsparta.dailywrite.domain.user.model.UserEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "storage")
@EntityListeners(AuditingEntityListener::class)
class UploadEntity (
    @Column (name = "fileurl", nullable = false)
    var fileUrl: String,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    var post : PostEntity?


){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

    @CreatedDate
    @Column (name = "created_at", nullable = false, updatable = false)
    lateinit var createdAt : LocalDateTime

}