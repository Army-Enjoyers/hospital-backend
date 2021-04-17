package com.armyenjoyers.hospital.schemas.annotations

@SchemaField
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonRadio(val id: String, val name: String, val values: Array<String>)
