package com.pyj.emailsender.presentation.user.dto

import com.pyj.emailsender.application.user.command.UserCommand

class UserRequestDTO {
    data class Regist(
        val userEmail: String,
        val userPassword: String,
        val nickname: String
    ) {
        fun toCommand(): UserCommand.Regist {
            return UserCommand.Regist(
                userEmail = userEmail,
                nickname = nickname,
                userPassword = userPassword
            )
        }
    }

    data class Login (
        val userEmail: String,
        val userPassword: String
    )

    data class UserInfo(
        val userCode: String
    ) {
        fun toCommand(): UserCommand.UserInfo {
            return UserCommand.UserInfo(
                userCode = userCode,
            )
        }
    }

    data class UserInfoByEmail(
        val userEmail: String
    ) {
        fun toCommand(): UserCommand.UserInfoByEmail {
            return UserCommand.UserInfoByEmail(
                userEmail = userEmail
            )
        }
    }

    data class ChageUserPassword(
        val userCode: String,
        val checkPassword: String,
        val newPassword: String
    ) {
        fun toCommand(): UserCommand.ChangeUserPassword {
            return UserCommand.ChangeUserPassword(
                userCode = userCode,
                checkPassword = checkPassword,
                newPassword = newPassword
            )
        }
    }

    data class ChangeUserNickname(
        val userCode: String,
        val userPassword: String,
        val newNickname: String
    ) {
        fun toCommand(): UserCommand.ChangeUserNickname {
            return UserCommand.ChangeUserNickname(
                userCode = userCode,
                userPassword = userPassword,
                newNickname = newNickname
            )
        }
    }

    data class ResetPassword(
        val userEmail: String,
        val verificationCode: String,
        val newPassword: String = "1234"
    )
}