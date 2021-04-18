package com.armyenjoyers.hospital.schema

interface SchemaMapper<T> {

    fun map(schemaClass: Class<*>): T

}