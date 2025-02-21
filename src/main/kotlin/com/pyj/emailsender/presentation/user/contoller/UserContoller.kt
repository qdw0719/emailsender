package com.pyj.emailsender.presentation.user.contoller

import com.pyj.emailsender.application.user.facade.UserFacade
import com.pyj.emailsender.domain.user.service.UserService
import com.pyj.emailsender.presentation.user.dto.UserRequestDTO
import com.pyj.emailsender.presentation.user.dto.UserResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController @RequestMapping("/api/users")
class UserContoller(
    private val userService: UserService,
    private val userFacade: UserFacade
) {

    @PostMapping("/regist")
    fun regist(@RequestBody request: UserRequestDTO.Regist): ResponseEntity<UserResponseDTO.Regist> {
        return ResponseEntity.ok(
            UserResponseDTO.Regist.toResponse(userService.regist(request.toCommand()))
        )
    }

    @PostMapping("/login")
    fun getCurrentUser(@RequestBody request: UserRequestDTO.Login): ResponseEntity<UserResponseDTO.UserLoginInfo> {
        return ResponseEntity.ok(
            UserResponseDTO.UserLoginInfo.toResponse(userFacade.login(request.userEmail, request.userPassword))
        )
    }

    @PostMapping("/user-info")
    fun findUserInfo(@RequestBody request: UserRequestDTO.UserInfo): ResponseEntity<UserResponseDTO.UserInfo> {
        return ResponseEntity.ok(
            UserResponseDTO.UserInfo.toResponse(userService.findUserInfo(request.toCommand()))
        )
    }

    @PostMapping("/forgot-password")
    fun forgotPassword(@RequestBody request: UserRequestDTO.UserInfoByEmail): ResponseEntity<UserResponseDTO.ResetMessage> {
        return ResponseEntity.ok(
            UserResponseDTO.ResetMessage.toResponse(userFacade.sendVertifyCode(request.toCommand()))
        )
    }

    @PostMapping("/auth/reset-password")
    fun resetPassword(@RequestBody request: UserRequestDTO.ResetPassword): ResponseEntity<UserResponseDTO.ResetMessage> { //@RequestHeader("Authorization") token: String,
        return ResponseEntity.ok(
            UserResponseDTO.ResetMessage.toResponse(userFacade.resetPassword(request.userEmail, request.verificationCode, request.newPassword))
        )
    }

    @PostMapping("/chage-password")
    fun changeUserPassWord(@RequestBody request: UserRequestDTO.ChageUserPassword): ResponseEntity<UserResponseDTO.ChangeResponse> {
        return ResponseEntity.ok(
            UserResponseDTO.ChangeResponse.toResponse(userService.changeUserPassword(request.toCommand()))
        )
    }

    @PostMapping("/chage-nickname")
    fun changeUserNickname(@RequestBody request: UserRequestDTO.ChangeUserNickname): ResponseEntity<UserResponseDTO.ChageUserNickname> {
        return ResponseEntity.ok(
            UserResponseDTO.ChageUserNickname.toResponse(userService.changeUserNickname(request.toCommand()))
        )
    }
}