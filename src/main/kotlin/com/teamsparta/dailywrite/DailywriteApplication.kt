package com.teamsparta.dailywrite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class DailywriteApplication

fun main(args: Array<String>) {
    runApplication<DailywriteApplication>(*args)
}
