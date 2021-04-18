package com.armyenjoyers.hospital.controller

import com.armyenjoyers.dg.DocGenerator
import com.armyenjoyers.dg.DocumentPath
import com.armyenjoyers.dg.documents.OutpatientCard
import com.armyenjoyers.dg.models.Patient
import com.armyenjoyers.hospital.controller.exception.IllegalIdException
import com.armyenjoyers.hospital.dto.PatientMedicalExtractDto
import com.armyenjoyers.hospital.dto.SchemaDto
import com.armyenjoyers.hospital.schema.Input
import com.armyenjoyers.hospital.schema.SchemaEntity
import com.armyenjoyers.hospital.schema.SchemaToInputsMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity

import java.nio.charset.StandardCharsets
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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

    @PostMapping("/{id}")
    fun createDocument(
        @PathVariable id: Int,
        @RequestBody patientMedicalExtractDto: PatientMedicalExtractDto,
        response: HttpServletResponse
    ): ResponseEntity<ByteArray> {
        checkIndex(id)
        val patientInfo = OutpatientCard(Patient())
        val document = DocGenerator.generate(patientInfo)
        val headers = HttpHeaders()
        val dateValue = DateTimeFormatter.ofPattern("YYYY-MM-dd_hh-mm-ss").format(LocalDateTime.now())
        val filename = "${schemas[id].name}_${dateValue}.${DocumentPath.docxExtension}"
            .replace(' ', '_')
        headers.contentDisposition = ContentDisposition.builder("attachment")
            .filename(filename, StandardCharsets.UTF_8)
            .build()

        return ResponseEntity(document.toByteArray(), headers, HttpStatus.OK)
    }

    private fun checkIndex(index: Int){
        if(index<0 || index>=schemas.size)
            throw IllegalIdException(index)
    }

}