package com.pyj.emailsender.presentation.interceptor

import com.pyj.emailsender.common.utils.JwtUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class JwtInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("Authorization")?.trim() ?: throw IllegalArgumentException("Authorization 헤더가 누락되었습니다.")
        if (!token.startsWith("Bearer ")) {
            throw IllegalArgumentException("유효하지 않은 토큰 형식입니다.")
        }

        val jwtToken = token.substring(7).trim()
        if (jwtToken.contains(" ")) {
            throw IllegalArgumentException("토큰에 불필요한 공백이 포함되어 있습니다.")
        }
        if (!JwtUtil.validateToken(jwtToken)) {
            throw IllegalArgumentException("유효하지 않은 인증 토큰입니다.")
        }

        return true
    }
}
