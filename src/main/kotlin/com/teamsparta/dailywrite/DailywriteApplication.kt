package com.teamsparta.dailywrite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
class DailywriteApplication

fun main(args: Array<String>) {
    runApplication<DailywriteApplication>(*args)
}
