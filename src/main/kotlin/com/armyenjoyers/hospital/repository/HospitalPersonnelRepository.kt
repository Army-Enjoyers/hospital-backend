package com.armyenjoyers.hospital.repository

import com.armyenjoyers.hospital.model.HospitalPersonnel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface HospitalPersonnelRepository: JpaRepository<HospitalPersonnel, Int> {
    
    fun fundByUserName(username: String?): HospitalPersonnel?

}