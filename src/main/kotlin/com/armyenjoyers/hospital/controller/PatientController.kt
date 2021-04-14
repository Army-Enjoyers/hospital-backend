package com.armyenjoyers.hospital.controller

import com.armyenjoyers.hospital.domain.patients.Patient
import com.armyenjoyers.hospital.service.PatientService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/patients")
class PatientController(
    private val patientService: PatientService
) {

    @GetMapping("/")
    fun getAll(): List<Patient> {
        return patientService.findAll()
    }

    @PostMapping("/")
    fun save(@RequestBody patient: Patient): Patient? {
        println(patient)
        return patientService.save(patient)
    }

    @GetMapping("/contingents")
    fun getAllContingents(
        @RequestParam(name = "like", required = false, defaultValue = "")
        contingentPart: String
    ): List<String> {
        return patientService.getAllContingentsLike(contingentPart)
    }

}