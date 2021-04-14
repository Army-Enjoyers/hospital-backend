package com.armyenjoyers.hospital.domain.patients

import org.springframework.data.annotation.Id

data class DisabilityType(
    @Id
    var id: Int?,

    val name: String,
)