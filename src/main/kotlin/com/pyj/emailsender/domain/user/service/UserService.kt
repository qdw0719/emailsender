package com.pyj.emailsender.domain.user.service

import com.pyj.emailsender.application.user.command.UserCommand
import com.pyj.emailsender.domain.user.User
import com.pyj.emailsender.domain.user.UserLoginHistory
import com.pyj.emailsender.domain.user.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService
) {
    fun regist(command: UserCommand.Regist): User{
        val user = User.create(command.userEmail, command.userPassword, command.nickname)
        return userRepository.save(user)
    }

    fun login(email: String, password: String): User {
        val userDetails = userDetailsService.loadUserByUsername(email)
        try {
            val authenticationToken = UsernamePasswordAuthenticationToken(userDetails.username, password, userDetails.authorities)
            val authentication: Authentication = authenticationManager.authenticate(authenticationToken)

            SecurityContextHolder.getContext().authentication = authentication
            return authentication.principal as User
        } catch (ex: AuthenticationException) {
            throw RuntimeException("아이디 또는 비밀번호가 잘못되었습니다.")
        }
    }

    @Transactional
    fun saveLoginHistory(user: User) {
        userRepository.loginHistorySave(UserLoginHistory.create(user.userCode))
    }

    fun findUserInfo(command: UserCommand.UserInfo): User {
        return userRepository.findUser(command.userCode)?: throw RuntimeException("해당하는 유저가 존재하지 않습니다.: $command.userCode")
    }

    fun findUserInfoByEmail(command: UserCommand.UserInfoByEmail): User {
        return userRepository.findUserByEmail(command.userEmail)?: throw RuntimeException("해당하는 유저가 존재하지 않습니다.: $command.userEmail")
    }

    @Transactional
    fun changeUserPassword(command: UserCommand.ChangeUserPassword): User {
        val user = userRepository.findUser(command.userCode)?: throw RuntimeException("해당하는 유저가 존재하지 않습니다.: $command.userCode")
        user.userValidation(user)
        user.updatePassword(command.newPassword)
        return userRepository.save(user)
    }

    @Transactional
    fun changeUserNickname(command: UserCommand.ChangeUserNickname): User {
        val user = userRepository.findUser(command.userCode)?: throw RuntimeException("해당하는 유저가 존재하지 않습니다.: $command.userCode")
        user.userValidation(user)
        user.passwordValidation(command.userPassword)
        user.updateNickname(command.newNickname)
        return userRepository.save(user)
    }

    @Transactional
    fun userToSeller(userCode: String) {
        val user = userRepository.findUser(userCode)?: throw RuntimeException("해당하는 유저가 존재하지 않습니다.: $userCode")
        user.updateUserToSeller()
        userRepository.save(user)
    }

    @Transactional
    fun sellerToUser(userCode: String) {
        val user = userRepository.findUser(userCode)?: throw RuntimeException("해당하는 유저가 존재하지 않습니다.: $userCode")
        user.updateSellerToUser()
        userRepository.save(user)
    }
}