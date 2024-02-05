package com.teamsparta.dailywrite.domain.user.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
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

    @Column(name = "send_date", nullable = false, updatable = false)
    @CreatedDate
    lateinit var sendDate: LocalDateTime
}