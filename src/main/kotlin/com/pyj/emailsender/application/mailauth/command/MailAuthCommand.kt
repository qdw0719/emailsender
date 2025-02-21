package com.pyj.emailsender.application.mailauth.command

import com.pyj.emailsender.domain.common.enums.MailAuthType


class MailAuthCommand {
    data class UserAuthInfo(
        val userCode: String,
        val verificationCode: String,
        val type: MailAuthType
    )
}