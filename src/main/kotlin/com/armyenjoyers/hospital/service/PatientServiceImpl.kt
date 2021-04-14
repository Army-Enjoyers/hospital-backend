package com.armyenjoyers.hospital.service

import com.armyenjoyers.hospital.domain.patients.Patient
import com.armyenjoyers.hospital.repository.PatientRepository
import com.armyenjoyers.hospital.service.exception.PatientNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service

@Service
class PatientServiceImpl
@Autowired constructor(
    private val patientRepository: PatientRepository,
    private val mongoTemplate: MongoTemplate,
): PatientService {

    override fun findAll(): List<Patient> {
        return patientRepository.findAll()
    }

    override fun findById(id: String): Patient {
        return patientRepository.findById(id).orElseThrow { PatientNotFoundException(id) }
    }

    override fun update(patientId: String, patient: Patient) {
        if(!patientRepository.existsById(patientId)){
            throw PatientNotFoundException(patientId)
        }
        patient.id = patientId
        patientRepository.save(patient)
    }

    override fun save(patient: Patient): Patient? {
        patient.id = null
        return patientRepository.save(patient)
    }

    override fun getAllContingentsLike(contingentPart: String): List<String> {
        return mongoTemplate.query(Patient::class.java)
            .distinct("contingent")
            .matching(Criteria.where("contingent").regex(".*${contingentPart}.*"))
            .`as`(String::class.java)
            .all()
    }
}