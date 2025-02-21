package com.pyj.emailsender.infra.db.user

import com.pyj.emailsender.domain.user.UserLoginHistory
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoginHistoryJpaRepository: JpaRepository<UserLoginHistory, Long> {
}