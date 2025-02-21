package com.pyj.emailsender.infra.db.user

import com.pyj.emailsender.domain.user.User
import com.pyj.emailsender.domain.user.UserLoginHistory
import com.pyj.emailsender.domain.user.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
    private val userLoginHistoryJpaRepository: UserLoginHistoryJpaRepository
): UserRepository {

    override fun findUser(userCode: String): User? {
        return userJpaRepository.findByUserCode(userCode)
    }

    override fun save(user: User): User {
        return userJpaRepository.save(user)
    }

    override fun findUserByEmail(userEmail: String): User? {
        return userJpaRepository.findByUserEmail(userEmail)
    }

    override fun loginHistorySave(userLoginHistory: UserLoginHistory) {
        userLoginHistoryJpaRepository.save(userLoginHistory)
    }
}