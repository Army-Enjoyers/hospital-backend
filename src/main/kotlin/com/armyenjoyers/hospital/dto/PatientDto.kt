package com.armyenjoyers.hospital.dto

import java.time.LocalDateTime

data class PatientDto(
    var firstName: String,
    var lastName: String,
    var patronymic: String,
    var gender: String,
    var job: String,
    var outpatientCardNumber: String?,
    var pensionerId: String?,
    var polyclinic: String,
    var additionalInfo: String,
    var birthDate: LocalDateTime,
    var citizaenship: String,

    var lifeAnamnesis: String,
    var contingent: String,
)
