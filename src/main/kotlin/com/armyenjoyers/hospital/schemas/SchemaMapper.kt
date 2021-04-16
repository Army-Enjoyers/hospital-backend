package com.armyenjoyers.hospital.schemas

import com.armyenjoyers.hospital.schemas.Input

interface SchemaMapper<T> {

    fun map(schemaClass: Class<*>): T

}