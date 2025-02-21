package com.pyj.emailsender.domain.user

import com.pyj.emailsender.domain.common.enums.ValidState
import com.pyj.emailsender.domain.user.converter.PasswordEncryptorConverter
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val userCode: String = UUID.randomUUID().toString(),
    @Column(nullable = false, unique = true)
    var userEmail: String,
    @Convert(converter = PasswordEncryptorConverter::class)
    var userPassword: String,
    var nickname: String,
    var lastLoginTime: LocalDateTime?,
    @Enumerated(EnumType.STRING)
    var userType: UserType,
    @Enumerated(EnumType.STRING)
    var validState: ValidState,
    var invalidDate: LocalDateTime?,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return emptyList()
    }

    override fun getPassword(): String = userPassword

    override fun getUsername(): String = userEmail

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = validState == ValidState.Valid

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = validState == ValidState.Valid

    companion object {
        fun create(
            userEmail: String,
            userPassword: String,
            nickname: String
        ): User {
            require(userEmail.isNotEmpty()) { "이메일은 필수 입력값 입니다." }
            require(userPassword.isNotEmpty()) { "비밀번호는 필수 입력값 입니다." }
            return User(
                userEmail = userEmail,
                userPassword = userPassword,
                nickname = nickname,
                lastLoginTime = null,
                userType = UserType.Normal,
                validState = ValidState.Valid,
                invalidDate = null,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        }
    }

    @PostUpdate fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }

    fun setLastLoginTime() {
        lastLoginTime = LocalDateTime.now()
    }

    fun userValidation(user: User) {
        if (user.validState.equals(ValidState.Invalid)) {
            throw RuntimeException("해당 아이디는 [ ${user.invalidDate} ] 자로 탈퇴된 아이디 입니다.")
        }
    }

    fun passwordValidation(checkPassword: String) {
        require(checkPassword.isNotEmpty())
        if (!checkPassword.equals(userPassword)) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        }
    }
    
    fun updatePassword(newUserPassword: String) {
        this.userPassword = newUserPassword
    }

    fun updateNickname(newNickname: String) {
        this.nickname = newNickname
    }

    fun updateUserToSeller() {
        userType = UserType.Seller
    }

    fun updateSellerToUser() {
        userType = UserType.Normal
    }

    fun userInvalid() {
        validState = ValidState.Invalid
    }
}