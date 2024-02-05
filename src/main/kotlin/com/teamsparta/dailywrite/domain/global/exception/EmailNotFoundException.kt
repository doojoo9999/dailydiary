package com.teamsparta.dailywrite.domain.global.exception

data class EmailNotFoundException (
    val modelName : String,
    val email : String,
) : RuntimeException (
    "Model $modelName not record of sending email: $email"
)