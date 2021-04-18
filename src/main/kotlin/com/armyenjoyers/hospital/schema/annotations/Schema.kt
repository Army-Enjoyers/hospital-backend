package com.armyenjoyers.hospital.schema.annotations

import org.springframework.beans.factory.annotation.Qualifier

@Qualifier("jsonSchema")
annotation class Schema(
    val name: String
)
