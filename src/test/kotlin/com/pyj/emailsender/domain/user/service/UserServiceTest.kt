package com.pyj.emailsender.domain.user.service

import com.pyj.emailsender.application.user.command.UserCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserServiceTest(
    @Autowired private val userService: UserService,
    @Autowired private val passwordEncoder: PasswordEncoder
) {

    @BeforeEach fun setup() {

    }

    @AfterEach fun teardown() {

    }

    @Test fun `회원가입 테스트`() {
        // given
        val userEmail = "test123user"
        val userPassword = "useruser"
        val nickname = "My Name Is User"

        val command = UserCommand.Regist(userEmail, userPassword, nickname)

        // when
        val newUser = userService.regist(command)

        // then
        assertThat(newUser).isNotNull()
        assertThat(newUser.userEmail).isEqualTo(userEmail)
        assertThat(newUser.nickname).isEqualTo(nickname)
    }

    @Test fun `비밀번호 변경 테스트`() {
        // given
        val userEmail = "test123user"
        val command = UserCommand.UserInfoByEmail(userEmail)
        val userInfo = userService.findUserInfoByEmail(command)
        val userCode = userInfo.userCode
        val newPassword = "test1234"

        // when
        userService.changeUserPassword(UserCommand.ChangeUserPassword(userInfo.userCode, userInfo.password, newPassword))

        // then
        val changedUserInfo = userService.findUserInfo(UserCommand.UserInfo(userCode))
        assertThat(changedUserInfo).isNotNull()
        assertThat(passwordEncoder.matches(newPassword, changedUserInfo.userPassword)).isTrue()
    }

    @Test fun `닉네임 변경 테스트`() {
        // given
        val userEmail = "test123user"
        val command = UserCommand.UserInfoByEmail(userEmail)
        val userInfo = userService.findUserInfoByEmail(command)
        val userCode = userInfo.userCode
        val newNickname = "user321test"

        // when
        userService.changeUserNickname(UserCommand.ChangeUserNickname(userInfo.userCode, userInfo.password, newNickname))

        // then
        val changedUserInfo = userService.findUserInfo(UserCommand.UserInfo(userCode))
        assertThat(changedUserInfo).isNotNull()
        assertThat(changedUserInfo.nickname).isEqualTo(newNickname)
    }
}