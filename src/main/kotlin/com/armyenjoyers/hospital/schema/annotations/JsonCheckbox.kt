package com.armyenjoyers.hospital.schema.annotations

@SchemaField
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonCheckbox(val id: String, val name: String)
