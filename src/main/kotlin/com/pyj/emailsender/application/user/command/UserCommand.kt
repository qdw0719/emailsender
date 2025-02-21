package com.pyj.emailsender.application.user.command

class UserCommand {
    data class Regist (
        val userEmail: String,
        val userPassword: String,
        val nickname: String
    )

    data class ChangeUserPassword (
        val userCode: String,
        val checkPassword: String? = "",
        val newPassword: String,
    )

    data class UserInfo(
        val userCode: String
    )

    data class UserInfoByEmail(
        val userEmail: String
    )

    data class ChangeUserNickname(
        val userCode: String,
        val userPassword: String,
        val newNickname: String
    )
}