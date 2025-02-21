package com.pyj.emailsender.common.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import java.util.*

object JwtUtil {

    private val SECRET_KEY = "your-secure-secret-key-which-is-at-least-256-bits-long"
    private val ALGORITHM = Algorithm.HMAC256(SECRET_KEY)

    fun generateToken(userCode: String, verificationCode: String): String {
        return JWT.create()
            .withSubject(userCode)
            .withClaim("verificationCode", verificationCode)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5분 만료
            .sign(ALGORITHM)
    }

    fun validateToken(token: String): Boolean {
        return try {
            val verifier = JWT.require(ALGORITHM).build()
            verifier.verify(token) // 토큰 검증
            true
        } catch (e: JWTVerificationException) {
            when (e) {
                is TokenExpiredException -> {
                    throw RuntimeException("토큰이 만료되었습니다.")
                }
                is SignatureVerificationException -> {
                    throw RuntimeException("토큰 서명이 일치하지 않습니다.")
                }
                else -> {
                    throw RuntimeException("유효하지 않은 토큰: ${e.message}")
                }
            }
            false
        }
    }

    fun extractClaims(token: String): DecodedJWT {
        return JWT.require(ALGORITHM)
            .build()
            .verify(token)
    }

    fun getUserCode(token: String): String {
        val decodedJWT = extractClaims(token)
        return decodedJWT.subject
    }

    fun getVerificationCode(token: String): String {
        val decodedJWT = extractClaims(token)
        return decodedJWT.getClaim("verificationCode").asString()
    }
}
