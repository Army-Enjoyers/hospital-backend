package com.armyenjoyers.hospital.service

import com.armyenjoyers.hospital.domain.patients.Patient

interface PatientService {
    fun findAll(): List<Patient>
    fun findById(id: String): Patient
    fun update(patientId: String, patient: Patient)
    fun save(patient: Patient): Patient?
    fun getAllContingentsLike(contingentPart: String): List<String>
}