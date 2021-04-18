package com.armyenjoyers.hospital.schema.annotations

@SchemaField
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonSelect(val id: String, val name: String, val values: Array<String>)
