package com.teamsparta.dailywrite.domain.message.controller

import com.teamsparta.dailywrite.domain.global.exception.UserNotFoundException
import com.teamsparta.dailywrite.domain.post.service.PostService
import com.teamsparta.dailywrite.infra.querydsl.QueryDslSupport
import com.teamsparta.dailywrite.infra.security.jwt.JwtPlugin
import io.kotest.assertions.any
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@Import(value = [QueryDslSupport::class])
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class PostControllerTest @Autowired constructor(
    private val mockMvc : MockMvc,
    private val jwtPlugin: JwtPlugin
) : DescribeSpec({

    afterContainer {
        clearAllMocks()
    }

    val postService = mockk<PostService>()


    describe("searchByNickname은"){
        context("nickname을 정확하게 입력했을 때"){
            it("pagenation이 정상적으로 적용되어야 한다.") {
                val nickname = "admin"
                val pageable = PageRequest.of(0,5, Sort.by("created_at"))

                every { postService.searchByNickname(pageable, nickname) } returns mockk()

                mockMvc.perform(
                    MockMvcRequestBuilders.get("/api/v1/posts/nickname")
                    .param("nickname", nickname))
                    .andExpect(MockMvcResultMatchers.status().isOk)

                verify { postService.searchByNickname(pageable, nickname) }

            }
        }
    }


    describe("searchByNickname에서") {
        context("Nickname을 찾지 못한 경우") {
            it("UserNotFoundException이 발생해야 한다.") {
                val nickname = "nonexistent"
                val pageable = PageRequest.of(0, 5, Sort.by("created_at"))
                val userId = 1L

                every { postService.searchByNickname(pageable, nickname) } throws UserNotFoundException(userId)

                assertThrows<UserNotFoundException> {
                    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts/nickname")
                        .param("nickname", nickname))
                }

                verify { postService.searchByNickname(pageable, nickname) }

            }
        }
    }

})