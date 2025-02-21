package com.pyj.emailsender.domain.user.service

import com.pyj.emailsender.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(userEmail: String): UserDetails {
        return userRepository.findUserByEmail(userEmail)?: throw UsernameNotFoundException("해당하는 유저가 존재하지 않습니다.: $userEmail")
    }
}