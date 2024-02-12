package com.teamsparta.dailywrite.domain.message.model

import com.teamsparta.dailywrite.domain.user.model.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.annotation.processing.Generated

@Entity
@Table(name = "message")
class MessageEntity(
    @Column(name = "title")
    val title : String,
    @Column(name = "content")
    val content : String,

    @ManyToOne
    @JoinColumn(name = "send_user_id")
    val sendUserId : UserEntity,

    @ManyToOne
    @JoinColumn(name = "receive_user_id")
    val receiveUserId : UserEntity,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

    @CreatedDate
    @Column(name = "created_at")
    lateinit var createdAt : LocalDateTime

    @Column(name = "read_at", nullable = true)
    var readAt : LocalDateTime? = null



    fun checkMessage () {
        this.readAt = LocalDateTime.now()
    }
}

