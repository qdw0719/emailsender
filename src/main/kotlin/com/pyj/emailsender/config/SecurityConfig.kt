package com.pyj.emailsender.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authz ->
                authz
                    .anyRequest().permitAll()
            }
            .formLogin { form ->
                form
                    .loginPage("/")
                    .successHandler(customAuthenticationSuccessHandler)
                    .permitAll()
            }
            .csrf { csrf ->
                csrf.ignoringRequestMatchers("/**")
            }
            .logout { logout ->
                logout.permitAll()
            }

        return http.build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}