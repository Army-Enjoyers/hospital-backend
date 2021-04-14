package com.armyenjoyers.hospital

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}
