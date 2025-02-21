package com.pyj.emailsender.domain.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import java.time.LocalDateTime

@Entity
data class UserLoginHistory (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userCode: String,
    var loginDateTime: LocalDateTime
) {

    companion object {
        fun create(
            userCode: String
        ): UserLoginHistory {
            return UserLoginHistory(
                userCode = userCode,
                loginDateTime = LocalDateTime.now()
            )
        }
    }

    @PrePersist fun onCreate() {
        loginDateTime = LocalDateTime.now()
    }
}
