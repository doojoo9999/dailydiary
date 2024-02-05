package com.teamsparta.dailywrite.domain.user.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name ="mails")
class MailEntity (

    @Column(name = "email", nullable = false)
    val email : String,

    @Column(name = "auth_code", nullable = false)
    val authCode : String,


){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0L

    @CreatedDate
    @Column(name = "send_date", updatable = false)
    lateinit var sendDate: LocalDateTime
}