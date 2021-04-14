package com.armyenjoyers.hospital.domain.patients

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Patient(
    @Id
    var id: Int?,

    var lastName: String,
    var firstName: String,
    var patronymic: String,
    var birthDate: LocalDateTime,
    var gender: String,
    var citizenship: String,
    var job: String?,
    var polyclinic: String,
    var pensionerId: String?,
    var outpatientCardNumber: String?,
    var additionalInfo: String?,

    val disabilities: MutableList<PatientDisability>,

    var contingent: Contingent,

    var lifeAnamnesis: LifeAnamnesis?,

    var militaryAffiliation: MilitaryAffiliation?
)
