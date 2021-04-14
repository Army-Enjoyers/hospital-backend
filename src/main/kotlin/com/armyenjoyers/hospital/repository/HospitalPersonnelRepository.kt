package com.armyenjoyers.hospital.repository

import com.armyenjoyers.hospital.domain.personnel.HospitalPersonnel
import org.springframework.data.jpa.repository.JpaRepository

interface HospitalPersonnelRepository: JpaRepository<HospitalPersonnel, Int> {
    
    fun findByLogin(login: String?): HospitalPersonnel?

}