package com.armyenjoyers.hospital.repository

import com.armyenjoyers.hospital.domain.personnel.Token
import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository: JpaRepository<Token, Int> {
    fun findByHospitalPersonnelId(hospitalPersonnelId: Int): Token?
}