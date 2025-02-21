package com.pyj.emailsender.presentation.scheduler

import com.pyj.emailsender.application.mailauth.facade.MailAuthFacade
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MailAuthSchduler(
    private val mailAuthFacade: MailAuthFacade
) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(MailAuthSchduler::class.java)
    }

    @Scheduled(fixedRate = 1000 * 60 * 1)
    fun invalidateExpiredAuthCodes() {
        val expiredSuccessList = mailAuthFacade.invalidateExpiredAuthCodes()
        log.info("만료처리된 인증번호 : ${expiredSuccessList.size}건")
    }
}