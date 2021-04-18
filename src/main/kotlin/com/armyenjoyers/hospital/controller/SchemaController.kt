package com.armyenjoyers.hospital.controller

import com.armyenjoyers.hospital.controller.exception.IllegalIdException
import com.armyenjoyers.hospital.dto.PatientMedicalExtractDto
import com.armyenjoyers.hospital.dto.SchemaDto
import com.armyenjoyers.hospital.schema.Input
import com.armyenjoyers.hospital.schema.SchemaEntity
import com.armyenjoyers.hospital.schema.SchemaToInputsMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/schemas")
class SchemaController
@Autowired constructor(
    @Qualifier("schemaEntities")
    private val schemas: List<SchemaEntity>,
    private val mapper: SchemaToInputsMapper
) {

    @GetMapping("/")
    fun getAll(): Any {
        return schemas.mapIndexed { index, entity ->
            SchemaDto(index, entity.name)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): List<Input>{
        if(id<0 || id>=schemas.size)
            throw IllegalIdException(id);
        return mapper.map(schemas[id].schemaClass)
    }

    @PostMapping("/")
    fun createDocument(@RequestBody patientMedicalExtractDto: PatientMedicalExtractDto, response: HttpServletResponse){

    }

}