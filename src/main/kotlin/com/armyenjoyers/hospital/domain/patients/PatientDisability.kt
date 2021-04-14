package com.armyenjoyers.hospital.domain.patients

import java.time.LocalTime

data class PatientDisability(
    var startDate: LocalTime,
    val disabilityType: DisabilityType,
)
