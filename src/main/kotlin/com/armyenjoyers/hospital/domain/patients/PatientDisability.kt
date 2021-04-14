package com.armyenjoyers.hospital.domain.patients

import java.time.LocalDate

data class PatientDisability(
    var startDate: LocalDate,
    val disabilityType: String,
)
