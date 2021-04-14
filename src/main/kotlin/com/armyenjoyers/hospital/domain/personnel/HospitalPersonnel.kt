package com.armyenjoyers.hospital.domain.personnel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("hospital_personnel")
data class HospitalPersonnel(

    @Id
    var id: String?,

    var firstName: String,

    var lastName: String,

    var patronymic: String,

    @Indexed(unique = true)
    val username: String,

    var password: String,

    var position: String,

    var roles: MutableList<Role>,

    var token: String
)