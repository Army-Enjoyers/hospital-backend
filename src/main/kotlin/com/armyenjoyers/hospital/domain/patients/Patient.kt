package com.armyenjoyers.hospital.domain.patients

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document("patients")
data class Patient(
    @Id
    var id: String?,

    var firstName: String,
    var lastName: String,
    var patronymic: String,
    var birthDate: LocalDate,
    var gender: String,

    var address: Address,
    var citizenship: String,
    var job: String?,
    var polyclinic: String,
    var pensionerId: String?,
    @Indexed(unique = true)
    var outpatientCardNumber: String,
    var additionalInfo: String?,

    val disabilities: MutableList<PatientDisability>,

    var contingent: String,

    var lifeAnamnesis: String?,

    @Indexed
    var militaryAffiliation: MilitaryAffiliation?
)
