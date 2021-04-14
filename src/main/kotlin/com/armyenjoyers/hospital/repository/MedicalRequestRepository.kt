package com.armyenjoyers.hospital.repository

import com.armyenjoyers.hospital.domain.requests.MedicalRequest
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicalRequestRepository: MongoRepository<MedicalRequest, String> {
}