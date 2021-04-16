package com.armyenjoyers.hospital.schemas

import com.armyenjoyers.hospital.schemas.annotations.JsonCheckbox
import com.armyenjoyers.hospital.schemas.annotations.JsonInput
import com.armyenjoyers.hospital.schemas.annotations.Schema

@Schema
data class SampleSchema (
    @JsonInput(id = "name", name="Имя пациента")
    val name: String,
    @JsonCheckbox(id = "married", name="Состоит в браке")
    val married: Boolean,
    val other: Int
)