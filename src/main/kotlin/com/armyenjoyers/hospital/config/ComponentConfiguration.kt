package com.armyenjoyers.hospital.config

import com.armyenjoyers.dg.DocGenerator
import com.armyenjoyers.dg.documents.OutpatientCard
import com.armyenjoyers.dg.models.Patient
import com.armyenjoyers.hospital.schema.SchemaEntity
import com.armyenjoyers.hospital.schema.annotations.JsonInputNumber
import com.armyenjoyers.hospital.schema.annotations.Schema
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.context.support.ReloadableResourceBundleMessageSource

import org.springframework.context.MessageSource
import java.util.*

@Configuration
class ComponentConfiguration {

    init {
        GlobalScope.launch{
            DocGenerator.generate(OutpatientCard(Patient()))
        }
    }

    @Bean
    @Qualifier("schemaProperties")
    fun schemaPropertiesMessageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:schema")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Qualifier("schemaEntities")
    fun schemaEntityList(): List<SchemaEntity> {
        return ClassScanner.findAllAnnotatedClassesInPackage(
            "com.armyenjoyers.hospital",
            Schema::class.java
        ).map{schemaClass ->
            val schemaName = schemaPropertiesMessageSource().getMessage(
                schemaClass.getAnnotation(Schema::class.java).name,
                null,
                Locale.getDefault()
            )
            SchemaEntity(
                schemaName,
                schemaClass
            )
        }
    }

}