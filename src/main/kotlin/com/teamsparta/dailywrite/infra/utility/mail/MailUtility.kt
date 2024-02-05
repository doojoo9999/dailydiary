package com.teamsparta.dailywrite.infra.utility.mail

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MailUtility(
    val javaMailSender : JavaMailSender
) {

    fun getRandomString() : String {
        val length = 6
        val randomId = UUID.randomUUID().toString().substring(0 until length)

        return randomId
    }

    fun sendMail(email: String) : String {
        val randomString = getRandomString()

        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)
        helper.setTo(email)
        helper.setSubject("Daily Diary 이메일 인증")
        helper.setText("인증 코드 : $randomString")
        helper.setFrom("doojoo0536@gmail.com")
        javaMailSender.send(message)


        return randomString
    }
}