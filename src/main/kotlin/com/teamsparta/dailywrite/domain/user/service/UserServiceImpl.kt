package com.teamsparta.dailywrite.domain.user.service

import com.teamsparta.dailywrite.domain.global.exception.EmailNotFoundException
import com.teamsparta.dailywrite.domain.user.dto.request.LoginRequest
import com.teamsparta.dailywrite.domain.user.dto.request.MailRequest
import com.teamsparta.dailywrite.domain.user.dto.request.SignUpRequest
import com.teamsparta.dailywrite.domain.user.dto.response.LoginResponse
import com.teamsparta.dailywrite.domain.user.dto.response.MailResponse
import com.teamsparta.dailywrite.domain.user.dto.response.UserResponse
import com.teamsparta.dailywrite.domain.user.model.MailEntity
import com.teamsparta.dailywrite.domain.user.model.UserEntity
import com.teamsparta.dailywrite.domain.user.model.UserRole
import com.teamsparta.dailywrite.domain.user.model.toResponse
import com.teamsparta.dailywrite.domain.user.repository.MailRepository
import com.teamsparta.dailywrite.domain.user.repository.UserRepository
import com.teamsparta.dailywrite.infra.security.jwt.JwtPlugin
import com.teamsparta.dailywrite.infra.utility.mail.MailUtility
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.time.LocalDateTime

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
    private val mailRepository: MailRepository,
    private val mailUtility: MailUtility
) : UserService {
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        val getAuthCode = mailRepository.findAllByEmail(request.email)
            ?: throw EmailNotFoundException("Email", request.email)

        if (!request.password.equals(request.checkPassword)){
            throw IllegalStateException("비밀번호와 확인 비밀번호가 서로 다릅니다.")
        }

        if (getAuthCode.none { it.authCode == request.authCode }) {
            throw IllegalStateException("인증 코드가 틀렸습니다.")
        }

        val now = LocalDateTime.now()
        val authMail = getAuthCode.find { it.authCode == request.authCode}
        if (authMail != null && Duration.between(authMail.sendDate, now).toMinutes() > 15)
            throw IllegalStateException("인증 번호가 만료된 상태입니다.")

        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("이미 존재하는 이메일입니다.")
        }

        mailRepository.deleteByEmail(request.email)

        return userRepository.save(
            UserEntity(
                nickname = request.nickname,
                email = request.email,
                password = passwordEncoder.encode(request.password),
                role = UserRole.Member
            )
        ).toResponse()
    }

    @Transactional
    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw EmailNotFoundException("Email", request.email)

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw IllegalStateException("비밀번호 입력 오류")
        }

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )


    }

    override fun sendMail(request: MailRequest): MailResponse {

        val randomString = mailUtility.sendMail(request.email)

        mailRepository.save(
            MailEntity(
                email = request.email,
                authCode = randomString
            )
        )

        return MailResponse(message = "메일 발송 완료")

    }
}