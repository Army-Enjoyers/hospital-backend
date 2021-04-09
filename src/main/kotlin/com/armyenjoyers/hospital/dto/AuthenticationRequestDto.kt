package com.armyenjoyers.hospital.dto

import com.sun.istack.NotNull
import org.springframework.validation.annotation.Validated

data class AuthenticationRequestDto(
    @NotNull
    val username: String,
    @NotNull
    val password: String
    )
