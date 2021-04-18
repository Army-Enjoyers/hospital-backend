package com.armyenjoyers.hospital.service.schemas

import com.armyenjoyers.hospital.schema.Input
import com.armyenjoyers.hospital.schema.SampleSchema
import com.armyenjoyers.hospital.schema.SchemaMapper
import com.armyenjoyers.hospital.schema.SchemaToInputsMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.context.MessageSource

internal class SchemaToInputsMapperTest {

    val mapper: SchemaMapper<List<Input>> = SchemaToInputsMapper(mock(MessageSource::class.java))

    @Test
    fun getSchema() {
        println(mapper.map(SampleSchema::class.java))
    }
}