package com.armyenjoyers.hospital.schema

import com.armyenjoyers.hospital.schema.annotations.JsonCheckbox
import com.armyenjoyers.hospital.schema.annotations.JsonInput
import com.armyenjoyers.hospital.schema.annotations.JsonRadio
import com.armyenjoyers.hospital.schema.annotations.Schema

@Schema("schema.test")
data class SampleSchema(
    @JsonInput(id = "name", name = "Имя пациента")
    val name: String,
    @JsonCheckbox(id = "married", name = "Состоит в браке")
    val married: Boolean,
    @JsonRadio(id = "salary", name = "Зарплата", values = ["<100$", "<500$", "<1000$", "Many"])
    val salary: String
)