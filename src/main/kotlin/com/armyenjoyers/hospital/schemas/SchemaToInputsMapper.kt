package com.armyenjoyers.hospital.schemas

import com.armyenjoyers.hospital.schemas.annotations.*
import com.armyenjoyers.hospital.schemas.exception.ClassIsNotSchemaException
import org.springframework.stereotype.Component
import java.lang.reflect.Field

@Component
class SchemaToInputsMapper : SchemaMapper<List<Input>> {

    private val cache: MutableMap<Class<*>, List<Input>> = HashMap()

    override fun map(schemaClass: Class<*>): List<Input> {
        if(cache.containsKey(schemaClass))
            return cache.get(schemaClass)!!

        if (!schemaClass.isAnnotationPresent(Schema::class.java)) {
            throw ClassIsNotSchemaException()
        }
        return schemaClass.declaredFields
            .filter {
                it.annotations.any { it.annotationClass.annotations.any { it is SchemaField } }
            }
            .map { declaredField ->
                Pair<Field, Annotation>(
                    declaredField,
                    declaredField.annotations.find { it.annotationClass.annotations.any { it is SchemaField } }!!
                )
            }
            .map { pair ->
                val field = pair.first
                val annotation = pair.second
                when (annotation) {
                    is JsonInput -> Input(InputType.TEXTAREA, annotation.id, annotation.name, emptyList())
                    is JsonCheckbox -> Input(InputType.CHECKBOX, annotation.id, annotation.name, emptyList())
                    is JsonRadio -> Input(InputType.RADIO, annotation.id, annotation.name, annotation.values.asList())
                    else -> throw UnmappedSchemaInputTypeException(
                        annotation.annotationClass.qualifiedName ?: "no type name"
                    )
                }
            }.also{
                cache.put(schemaClass, it)
            }
    }

}