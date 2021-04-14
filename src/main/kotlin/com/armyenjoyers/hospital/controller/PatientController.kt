package com.armyenjoyers.hospital.controller

import com.armyenjoyers.hospital.domain.patients.Patient
import com.armyenjoyers.hospital.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/patients")
class PatientController
@Autowired constructor(
    private val patientRepository: PatientRepository
) {

    @GetMapping("/")
    fun getAll(): MutableList<Patient> {
        return patientRepository.findAll()
    }

    @PostMapping("/")
    fun save(@RequestBody patient: Patient): Patient? {
        println(patient)
        return patientRepository.save(patient)
    }

}