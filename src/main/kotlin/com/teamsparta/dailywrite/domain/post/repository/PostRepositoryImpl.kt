package com.teamsparta.dailywrite.domain.post.repository

import com.querydsl.core.QueryFactory
import com.teamsparta.dailywrite.domain.post.model.PostEntity
import com.teamsparta.dailywrite.domain.post.model.QPostEntity
import com.teamsparta.dailywrite.domain.user.model.QUserEntity
import com.teamsparta.dailywrite.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Repository
import java.awt.print.Pageable
import java.time.LocalDateTime

@Repository
class PostRepositoryImpl : QueryDslSupport(), CustomPostRepository {

    private val post = QPostEntity.postEntity
    private val user = QUserEntity.userEntity
    override fun findByPageable(pageable: org.springframework.data.domain.Pageable): Page<PostEntity> {

        //총 개수
        val totalCount = queryFactory
            .select(post.count())
            .from(post)
            .fetchOne() ?: 0L

        //페이지에 게시글
        val pageCount = queryFactory
            .selectFrom(post)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        return PageImpl(pageCount, pageable, totalCount)


    }

    override fun searchByTitle(title: String, pageable: org.springframework.data.domain.Pageable): Page<PostEntity> {

        //총 개수
        val totalCount = queryFactory
            .select(post.count())
            .from(post)
            .where(post.title.containsIgnoreCase(title))
            .fetchOne() ?: 0L

        //페이지에 게시글
        val pageCount = queryFactory
            .selectFrom(post)
            .where(post.title.containsIgnoreCase(title))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()


        return PageImpl(pageCount, pageable, totalCount)
    }

    override fun searchByNickname(nickname: String, pageable : org.springframework.data.domain.Pageable): Page<PostEntity> {

        // 이거도 총 개수부터
        val totalCount = queryFactory
            .select(post.count())
            .from(post)
            .join(post.user, user)
            .where (user.nickname.containsIgnoreCase(nickname))
            .fetchOne() ?: 0L

        // 페이지에 몇번째?
        val pageCount = queryFactory
            .select(post)
            .from(post)
            .join(post.user, user)
            .where (user.nickname.containsIgnoreCase(nickname))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        return PageImpl(pageCount, pageable, totalCount)
    }

    override fun searchByDate (date: LocalDateTime, pageable : org.springframework.data.domain.Pageable) : Page<PostEntity> {

        //총 개수부터
        val totalCount = queryFactory
            .select(post.count())
            .from(post)
            .where(post.createdAt.eq(date))
            .orderBy(post.createdAt.asc())
            .fetchOne() ?: 0L

        val pageCount = queryFactory
            .selectFrom(post)
            .where(post.createdAt.eq(date))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        return PageImpl(pageCount, pageable, totalCount)
    }
}