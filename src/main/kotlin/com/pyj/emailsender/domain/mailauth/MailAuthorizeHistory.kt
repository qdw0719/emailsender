package com.pyj.emailsender.domain.mailauth

import com.pyj.emailsender.domain.common.enums.MailAuthType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class MailAuthorizeHistory (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userCode: String,
    val verificationCode: String,
    @Enumerated(EnumType.STRING)
    val type: MailAuthType,
    var createdAt: LocalDateTime,
) {
    companion object {
        fun create(
            userCode: String,
            verificationCode: String,
            type: MailAuthType
        ): MailAuthorizeHistory {
            return MailAuthorizeHistory(
                userCode = userCode,
                verificationCode = verificationCode,
                type = type,
                createdAt = LocalDateTime.now(),
            )
        }
    }
}
