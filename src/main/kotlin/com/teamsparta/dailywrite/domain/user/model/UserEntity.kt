package com.teamsparta.dailywrite.domain.user.model

import com.teamsparta.dailywrite.domain.user.dto.response.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity (
    @Column(name = "email", nullable = false)
    val email : String,

    @Column(name = "password", nullable = false)
    val password : String,

    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    val role : UserRole
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0
}

fun UserEntity.toResponse() : UserResponse {
    return UserResponse(
        id = id,
        email = email,
        role = role
    )
}