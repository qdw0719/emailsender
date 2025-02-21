package com.pyj.emailsender.domain.mailauth

import com.pyj.emailsender.domain.common.enums.ValidState
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Entity
data class MailAuthorize(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = true, nullable = false)
    val userCode: String,
    var verificationCode: String,
    var sendCount: Int,
    @Enumerated(EnumType.STRING)
    var validState: ValidState,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
) {
    companion object {
        fun create(userCode: String, verificationCode: String, sendCount: Int): MailAuthorize {
            require(userCode.isNotEmpty()) { "유저고유 식별코드가 누락되었습니다." }
            return MailAuthorize(
                userCode = userCode,
                verificationCode = verificationCode,
                sendCount = sendCount,
                validState = ValidState.Valid,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }

    @PostUpdate fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }

    fun validate() {
        validState = ValidState.Valid
    }

    fun invalidate() {
        validState = ValidState.Invalid
    }

    fun verificationCodeValidate(inputVerificationCode: String) {
        if (!verificationCode.equals(inputVerificationCode)) {
            throw RuntimeException("인증번호가 일치하지 않습니다.")
        }
    }

    fun isValid() {
        if (validState != ValidState.Valid) {
            throw RuntimeException("인증번호가 유효하지 않습니다. 재발급 후 인증해주세요.")
        }
    }

    fun increaseSendCount() {
        sendCount = sendCount + 1
    }

    fun newVerificationCode(newVerificationCode: String) {
        verificationCode = newVerificationCode
    }

    fun isExpired(): Boolean {
        val currentTime = LocalDateTime.now()
        return ChronoUnit.MINUTES.between(updatedAt, currentTime) > 5
    }
}
