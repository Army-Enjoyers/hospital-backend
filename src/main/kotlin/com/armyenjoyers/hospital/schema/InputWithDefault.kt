package com.armyenjoyers.hospital.schema

class InputWithDefault(
    inputType: InputType,
    id: String,
    name: String,
    var inputValues: List<String>
    )
    : Input(inputType, id, name) {
}