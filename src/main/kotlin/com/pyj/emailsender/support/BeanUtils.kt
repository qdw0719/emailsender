package com.pyj.emailsender.support

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
object BeanUtils : ApplicationContextAware {

    private lateinit var applicationContext: ApplicationContext

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        BeanUtils.applicationContext = applicationContext
    }

    fun <T> getBean(clazz: Class<T>): T {
        return applicationContext.getBean(clazz)
    }
}