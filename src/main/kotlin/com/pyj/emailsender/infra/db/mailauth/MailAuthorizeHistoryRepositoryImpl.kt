package com.pyj.emailsender.infra.db.mailauth

import com.pyj.emailsender.domain.mailauth.MailAuthorizeHistory
import com.pyj.emailsender.domain.mailauth.repository.MailAuthorizeHistoryRepository
import org.springframework.stereotype.Repository

@Repository
class MailAuthorizeHistoryRepositoryImpl(
    private val mailAuthorizeHistoryJpaRepository: MailAuthorizeHistoryJpaRepository
): MailAuthorizeHistoryRepository {
    override fun save(mailAuthorizeHistory: MailAuthorizeHistory): MailAuthorizeHistory {
        return mailAuthorizeHistoryJpaRepository.save(mailAuthorizeHistory)
    }

    override fun saveAll(mailAuthorizeHistories: List<MailAuthorizeHistory>) {
        mailAuthorizeHistoryJpaRepository.saveAll(mailAuthorizeHistories)
    }
}