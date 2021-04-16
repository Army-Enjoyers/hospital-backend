package com.armyenjoyers.hospital.schemas.annotations

@SchemaField
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonInput(val id: String, val name: String){

}
