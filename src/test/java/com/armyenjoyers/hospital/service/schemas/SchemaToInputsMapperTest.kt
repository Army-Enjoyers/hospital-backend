package com.armyenjoyers.hospital.service.schemas

import com.armyenjoyers.hospital.schemas.SampleSchema
import com.armyenjoyers.hospital.schemas.SchemaMapper
import com.armyenjoyers.hospital.schemas.SchemaToInputsMapper
import org.junit.jupiter.api.Test

internal class SchemaToInputsMapperTest {

    val mapper: SchemaMapper = SchemaToInputsMapper()

    @Test
    fun getSchema() {
        println(mapper.getSchema(SampleSchema::class.java))
    }
}