package com.armyenjoyers.hospital.schema

open class Input(
    val type: InputType,
    val id: String,
    var name: String,
    val isMultiple: Boolean = false,
)