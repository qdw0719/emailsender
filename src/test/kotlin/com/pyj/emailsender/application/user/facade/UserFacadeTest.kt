package com.pyj.emailsender.application.user.facade

import com.pyj.emailsender.application.user.command.UserCommand
import com.pyj.emailsender.domain.user.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import kotlin.test.Test

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserFacadeTest(
    @Autowired private val userFacade: UserFacade,
    @Autowired private val userService: UserService
) {

    @Test fun `로그인 테스트`() {
        // given
        val userEmail = "pyj19960719@gmail.com"
        val userPassword = "admin"

        // when
        val loginUser = userFacade.login(userEmail, userPassword)

        // then
        val userInfo = userService.findUserInfoByEmail(UserCommand.UserInfoByEmail(userEmail))

        assertThat(loginUser).isNotNull()
        assertThat(userInfo.userCode).isEqualTo(loginUser.userCode)
    }

    @Test fun `메일 전송 테스트`() {
        // given
        val userEmail = "qdw0719@naver.com";

        // when
        val token = userFacade.sendVertifyCode(UserCommand.UserInfoByEmail(userEmail))

        // then
        assertThat(token).isNotNull()
    }
}