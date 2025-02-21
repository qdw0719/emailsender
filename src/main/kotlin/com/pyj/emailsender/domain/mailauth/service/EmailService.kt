package com.pyj.emailsender.domain.mailauth.service

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun sendVerificationCode(userEmail: String, verificationCode: String) {
        val title = "e-commerce 인증번호"
        val content = StringBuilder()
        content.append("<div style='font-family: malgun gothic;'>")
        content.append("    <h3>E-Commerce에서 보낸 인증번호 입니다.</h3>")
        content.append("    <div style='text-align: left; vertical-align: center;'>")
        content.append("        <div>인증번호를 비밀번호 초기화 화면에 입력해주세요.</div>")
        content.append("        <div>")
        content.append("            인증번호: <span style='font-weight: 700; color: blue; font-size: 20px;'>$verificationCode</span>")
        content.append("        </div>")
        content.append("    </div>")
        content.append("</div>")

        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")

        helper.setTo(userEmail)
        helper.setSubject(title)
        helper.setText(content.toString(), true)

        try {
            javaMailSender.send(message)
        } catch(e: Exception) {
            throw RuntimeException("메일전송에 실패했습니다. 다시 시도해주세요.", e)
        }
    }
}