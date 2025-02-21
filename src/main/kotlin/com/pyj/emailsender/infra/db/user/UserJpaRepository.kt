package com.pyj.emailsender.infra.db.user


import com.pyj.emailsender.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<User, Long> {

    fun save(user: User): User

    fun findByUserCode(userCode: String): User?

    fun findByUserEmail(userEmail: String): User?
}