package com.pyj.emailsender.infra.db.mailauth

import com.pyj.emailsender.domain.common.enums.ValidState
import com.pyj.emailsender.domain.mailauth.MailAuthorize
import com.pyj.emailsender.domain.mailauth.repository.MailAuthorizeRepository
import org.springframework.stereotype.Repository

@Repository
class MailAuthorizeRepositoryImpl(
    private val mailAuthorizeJpaRepository: MailAuthorizeJpaRepository
): MailAuthorizeRepository {
    override fun save(userAuthInfo: MailAuthorize) {
        mailAuthorizeJpaRepository.save(userAuthInfo)
    }

    override fun saveAll(expiredTargetList: List<MailAuthorize>): List<MailAuthorize> {
        return mailAuthorizeJpaRepository.saveAll(expiredTargetList)
    }

    override fun findUserAuthInfo(userCode: String): MailAuthorize? {
        return mailAuthorizeJpaRepository.findByUserCode(userCode)
    }

    override fun getAllMailAuthorizeUsers(): List<MailAuthorize> {
        return mailAuthorizeJpaRepository.findAllByValidState(ValidState.Valid)
    }

    override fun findUserAuthInfoByValid(userCode: String): MailAuthorize? {
        return mailAuthorizeJpaRepository.findByUserCodeAndValidState(userCode, ValidState.Valid)
    }
}