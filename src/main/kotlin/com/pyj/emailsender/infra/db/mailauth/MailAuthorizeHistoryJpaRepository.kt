package com.pyj.emailsender.infra.db.mailauth

import com.pyj.emailsender.domain.mailauth.MailAuthorizeHistory
import org.springframework.data.jpa.repository.JpaRepository

interface MailAuthorizeHistoryJpaRepository: JpaRepository<MailAuthorizeHistory, Long> {
}