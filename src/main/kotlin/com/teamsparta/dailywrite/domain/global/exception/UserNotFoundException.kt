package com.teamsparta.dailywrite.domain.global.exception

data class UserNotFoundException(
    val id : Long,
) : RuntimeException (
    "User Not Found id: $id"
)