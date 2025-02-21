package com.pyj.emailsender.domain.mailauth.repository

import com.pyj.emailsender.domain.mailauth.MailAuthorizeHistory

interface MailAuthorizeHistoryRepository {
    fun save(mailAuthorizeHistory: MailAuthorizeHistory): MailAuthorizeHistory

    fun saveAll(mailAuthorizeHistories: List<MailAuthorizeHistory>)
}