package com.armyenjoyers.hospital.repository

import com.armyenjoyers.hospital.domain.patients.Patient
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository: MongoRepository<Patient, String> {
}