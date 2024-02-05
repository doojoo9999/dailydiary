package com.teamsparta.dailywrite.domain.exception

data class EmailNotFoundException (
    val modelName : String,
    val email : String,
) : RuntimeException (
    "Model $modelName not record of sending email: $email"
)