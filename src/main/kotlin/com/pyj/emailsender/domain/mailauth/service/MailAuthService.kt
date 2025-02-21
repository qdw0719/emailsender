package com.pyj.emailsender.domain.mailauth.service

import com.pyj.emailsender.application.mailauth.command.MailAuthCommand
import com.pyj.emailsender.domain.mailauth.MailAuthorize
import com.pyj.emailsender.domain.mailauth.MailAuthorizeHistory
import com.pyj.emailsender.domain.mailauth.repository.MailAuthorizeHistoryRepository
import com.pyj.emailsender.domain.mailauth.repository.MailAuthorizeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MailAuthService(
    private val mailAuthorizeRepository: MailAuthorizeRepository,
    private val mailAuthorizeHistoryRepository: MailAuthorizeHistoryRepository
) {

    @Transactional
    fun createHistory(command: MailAuthCommand.UserAuthInfo) {
        val mailAuthorizeHistory = MailAuthorizeHistory.create(command.userCode, command.verificationCode, command.type)
        mailAuthorizeHistoryRepository.save(mailAuthorizeHistory)
    }

    @Transactional
    fun saveUserAuthorize(command: MailAuthCommand.UserAuthInfo) {
        val userAuthInfo = mailAuthorizeRepository.findUserAuthInfo(command.userCode)
            ?.apply {
                increaseSendCount()
                newVerificationCode(command.verificationCode)
                validate()
            }
            ?: MailAuthorize.create(command.userCode, command.verificationCode, 1)
        mailAuthorizeRepository.save(userAuthInfo)
    }

    fun findByUserAuthInfo(command: MailAuthCommand.UserAuthInfo): MailAuthorize? {
        return mailAuthorizeRepository.findUserAuthInfoByValid(command.userCode)
    }

    fun getAllMailAuthorizeUsers(): List<MailAuthorize> {
        return mailAuthorizeRepository.getAllMailAuthorizeUsers();
    }
}