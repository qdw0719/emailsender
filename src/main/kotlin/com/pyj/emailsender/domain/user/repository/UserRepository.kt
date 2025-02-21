package com.pyj.emailsender.domain.user.repository

import com.pyj.emailsender.domain.user.User
import com.pyj.emailsender.domain.user.UserLoginHistory

interface UserRepository {
    fun findUser(userCode: String): User?

    fun save(user: User): User

    fun findUserByEmail(userEmail: String): User?

    fun loginHistorySave(userLoginHistory: UserLoginHistory)
}