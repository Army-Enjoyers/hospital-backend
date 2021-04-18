package com.armyenjoyers.hospital.schema.annotations

@SchemaField
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonInput(val id: String, val name: String, val notEmpty: Boolean = false, val isMultiple: Boolean = false){

}
