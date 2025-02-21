package com.pyj.emailsender.config

import com.pyj.emailsender.presentation.interceptor.JwtInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val jwtInterceptor: JwtInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
//        registry.addInterceptor(jwtInterceptor)
//                .addPathPatterns("/api/users/auth/reset-password")
    }
}
