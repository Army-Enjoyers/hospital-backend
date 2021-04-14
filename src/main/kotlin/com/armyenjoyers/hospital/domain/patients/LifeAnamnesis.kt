package com.armyenjoyers.hospital.domain.patients

import org.springframework.data.annotation.Id


data class LifeAnamnesis(
    @Id
    var id: Int?,

    val value: String
)