package com.pyj.emailsender.infra.db.mailauth

import com.pyj.emailsender.domain.common.enums.ValidState
import com.pyj.emailsender.domain.mailauth.MailAuthorize
import org.springframework.data.jpa.repository.JpaRepository

interface MailAuthorizeJpaRepository: JpaRepository<MailAuthorize, Long> {
    fun findByUserCode(userCode: String): MailAuthorize?

    fun findByUserCodeAndValidState(userCode: String, validState: ValidState): MailAuthorize?

    fun findAllByValidState(validState: ValidState): List<MailAuthorize>
}