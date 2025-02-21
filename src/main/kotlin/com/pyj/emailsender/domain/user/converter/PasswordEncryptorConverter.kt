package com.pyj.emailsender.domain.user.converter

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class PasswordEncryptorConverter: AttributeConverter<String, String> {

    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

    override fun convertToDatabaseColumn(attribute: String): String {
        return passwordEncoder.encode(attribute)
    }

    override fun convertToEntityAttribute(dbData: String): String {
        return dbData
    }
}
