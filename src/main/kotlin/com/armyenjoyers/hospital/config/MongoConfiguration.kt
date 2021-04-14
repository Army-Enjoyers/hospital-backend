package com.armyenjoyers.hospital.config

import org.springframework.data.mongodb.core.MongoTemplate

import com.mongodb.client.MongoClients

import com.mongodb.client.MongoClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MongoConfiguration {

    @Value("\${database.hostname}")
    lateinit var host: String
    @Value("\${database.port}")
    lateinit var port: String
    @Value("\${database.name}")
    lateinit var dbName: String

    @Bean
    fun mongoClient(): MongoClient? {
        return MongoClients.create("mongodb://${host}:${port}")
    }

    @Bean
    fun mongoTemplate(): MongoTemplate? {
        return MongoTemplate(mongoClient()!!, dbName)
    }
}