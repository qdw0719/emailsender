package com.pyj.emailsender.presentation.user.dto

import com.pyj.emailsender.domain.user.User
import java.time.LocalDateTime

class UserResponseDTO{
    data class Regist(
        val userCode: String,
        val userEmail: String,
        val nickname: String
    ) {
        companion object {
            fun toResponse(user: User): Regist {
                return Regist(
                    userCode = user.userCode,
                    userEmail = user.userEmail,
                    nickname = user.nickname
                )
            }
        }
    }

    data class UserLoginInfo(
        val userCode: String,
        val userEmail: String
    ) {
        companion object {
            fun toResponse(user: User): UserLoginInfo {
                return UserLoginInfo(
                    userCode = user.userCode,
                    userEmail = user.userEmail
                )
            }
        }
    }

    data class UserInfo(
        val userCode: String,
        val userEmail: String,
        val nickname: String,
        val lastLoginTime: LocalDateTime?,
    ) {
        companion object {
            fun toResponse(user: User): UserInfo {
                return UserInfo(
                    userCode = user.userCode,
                    userEmail = user.userEmail,
                    nickname = user.nickname,
                    lastLoginTime = user.lastLoginTime
                )
            }
        }
    }

    data class ChangeResponse(
        val userCode: String,
        val userEmail: String
    ) {
        companion object {
            fun toResponse(user: User): ChangeResponse {
                return ChangeResponse(
                    userCode = user.userCode,
                    userEmail = user.userEmail
                )
            }
        }
    }

    data class ChageUserNickname(
        val userCode: String,
        val nickname: String
    ) {
        companion object {
            fun toResponse(user: User): ChageUserNickname {
                return ChageUserNickname(
                    userCode = user.userCode,
                    nickname = user.nickname
                )
            }
        }
    }

    data class ResetMessage(
        val message: String
    ) {
        companion object {
            fun toResponse(message: String): ResetMessage {
                return ResetMessage(message)
            }
        }
    }
}
