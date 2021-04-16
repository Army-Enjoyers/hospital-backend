package com.armyenjoyers.hospital.schemas

import com.armyenjoyers.hospital.schemas.annotations.JsonCheckbox
import com.armyenjoyers.hospital.schemas.annotations.JsonInput
import com.armyenjoyers.hospital.schemas.annotations.Schema
import com.armyenjoyers.hospital.schemas.annotations.SchemaField
import com.armyenjoyers.hospital.schemas.exception.ClassIsNotSchemaException
import org.springframework.stereotype.Component

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
                it.annotations.any{it.annotationClass.annotations.any{it is SchemaField } }
            }
            .map { declaredField ->
                declaredField.annotations.find{ it.annotationClass.annotations.any{it is SchemaField } }!!
            }
            .map { annotation ->
                when {
                    annotation is JsonInput -> Input(InputType.TEXTAREA, annotation.id, annotation.name)
                    annotation is JsonCheckbox -> Input(InputType.CHECKBOX, annotation.id, annotation.name)
                    else -> throw UnmappedSchemaInputTypeException(annotation.annotationClass.qualifiedName?:"no type name")
                }
            }.also{
                cache.put(schemaClass, it)
            }
    }

}