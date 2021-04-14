package com.armyenjoyers.hospital.service

import com.armyenjoyers.hospital.domain.personnel.HospitalPersonnel
import com.armyenjoyers.hospital.dto.RegistrationRequestDto

interface HospitalPersonnelService {
    fun register(personnel: HospitalPersonnel): HospitalPersonnel
    fun findAll(): List<HospitalPersonnel>
    fun findByUsername(username: String?): HospitalPersonnel?
    fun findById(id: String): HospitalPersonnel?
    fun register(registrationRequestDto: RegistrationRequestDto)
}