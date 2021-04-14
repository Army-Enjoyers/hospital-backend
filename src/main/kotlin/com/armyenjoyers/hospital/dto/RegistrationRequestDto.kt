package com.armyenjoyers.hospital.dto

data class RegistrationRequestDto(
    val username: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    var password: String,
    val position: String
)