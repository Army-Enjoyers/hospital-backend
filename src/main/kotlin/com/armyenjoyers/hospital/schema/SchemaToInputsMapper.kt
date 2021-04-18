package com.armyenjoyers.hospital.schema

import com.armyenjoyers.hospital.schema.annotations.*
import com.armyenjoyers.hospital.schema.exception.ClassIsNotSchemaException
import com.armyenjoyers.hospital.schema.exception.UnmappedSchemaInputTypeException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

@Component
class SchemaToInputsMapper
@Autowired constructor(
    @Qualifier("schemaProperties")
    private val messageSource: MessageSource
) : SchemaMapper<List<Input>> {

    private val cache: MutableMap<Class<*>, List<Input>> = HashMap()

    override fun map(schemaClass: Class<*>): List<Input> {
        if(cache.containsKey(schemaClass))
            return cache[schemaClass]!!

        if (!schemaClass.isAnnotationPresent(Schema::class.java)) {
            throw ClassIsNotSchemaException()
        }
        return schemaClass.declaredFields
            .filter {
                it.annotations.any { it.annotationClass.annotations.any { it is SchemaField } }
            }
            .map { declaredField ->
                    declaredField.annotations.find { it.annotationClass.annotations.any { it is SchemaField } }!!
            }
            .map { annotation ->
                when (annotation) {
                    is JsonInput -> Input(InputType.INPUT, annotation.id, annotation.name,
                        isMultiple = annotation.isMultiple)
                    is JsonInputNumber -> Input(InputType.INPUT, annotation.id, annotation.name)
                    is JsonCheckbox -> Input(InputType.CHECKBOX, annotation.id, annotation.name)
                    is JsonRadio -> InputWithDefault(InputType.RADIO, annotation.id, annotation.name, annotation.values.asList())
                    is JsonDate -> Input(InputType.DATE, annotation.id, annotation.name)
                    is JsonSelect -> InputWithDefault(InputType.RADIO, annotation.id, annotation.name, annotation.values.asList())
                    else -> throw UnmappedSchemaInputTypeException(
                        annotation.annotationClass.qualifiedName ?: "no type name"
                    )
                }
            }.map{mapProperties(it)}
            .also{
                cache.put(schemaClass, it)
            }
    }

    private fun mapProperties(input: Input): Input {
        val getProperty = {key: String -> messageSource.getMessage(key, null, Locale.getDefault())}
        try {
            input.name = getProperty(input.name)
            if(input is InputWithDefault)
            input.inputValues = input.inputValues.map { getProperty(it) }
        }catch (e: NoSuchMessageException){
            e.printStackTrace()
        }
        return input
    }

}