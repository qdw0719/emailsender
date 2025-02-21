package com.pyj.emailsender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class EmailSenderApplication

fun main(args: Array<String>) {
	runApplication<EmailSenderApplication>(*args)
}
