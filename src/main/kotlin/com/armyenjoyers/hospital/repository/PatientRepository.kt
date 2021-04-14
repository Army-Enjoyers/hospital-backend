package com.armyenjoyers.hospital.repository

import com.armyenjoyers.hospital.domain.patients.Patient
import org.springframework.data.jpa.repository.JpaRepository

interface PatientRepository: JpaRepository<Patient, Int> {
}