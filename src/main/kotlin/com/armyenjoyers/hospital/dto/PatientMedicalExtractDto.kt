package com.armyenjoyers.hospital.dto

import com.armyenjoyers.hospital.schema.annotations.*
import java.time.LocalDate

@Schema("schema.patientMedicalExtract")
data class PatientMedicalExtractDto(
    @JsonInput(id = "firstName", name = "patient.firstName")
    var firstName: String,
    @JsonInput(id = "lastName", name = "patient.lastName")
    var lastName: String,
    @JsonInput(id = "patronymic", name = "patient.patronymic")
    var patronymic: String,
    @JsonDate(id = "birthDate", name = "patient.birthDate")
    var birthDate: LocalDate,
    @JsonRadio(id = "gender", name = "patient.gender", values = ["patient.gender.male", "patient.gender.female"])
    var gender: String,

    @JsonInput(id = "address", name = "patient.address")
    var address: String,

    @JsonInput(id = "extractReason", name = "patient.extractReason")
    var extractReason: String,
    @JsonInput(id = "disabilities", name = "patient.disabilities", isMultiple = true)
    val disabilities: MutableList<String>,
    @JsonInput(id = "additions", name = "patient.additions", isMultiple = true)
    val additions: MutableList<String>,
)
