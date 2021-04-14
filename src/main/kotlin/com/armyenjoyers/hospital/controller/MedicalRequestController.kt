package com.armyenjoyers.hospital.controller

import com.armyenjoyers.hospital.domain.requests.MedicalRequest
import com.armyenjoyers.hospital.repository.MedicalRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/medical-requests")
class MedicalRequestController
@Autowired
constructor(
    private val medicalRequestRepository: MedicalRequestRepository
){

    @GetMapping("/")
    fun getAll(): MutableList<MedicalRequest> {
        return medicalRequestRepository.findAll()
    }


}