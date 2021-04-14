package com.armyenjoyers.hospital.repository

import com.armyenjoyers.hospital.domain.personnel.HospitalPersonnel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface HospitalPersonnelRepository : MongoRepository<HospitalPersonnel, Int> {
    fun findByUsername(username: String?): HospitalPersonnel?
}