package com.armyenjoyers.hospital.controller

import com.armyenjoyers.hospital.domain.certificates.HospitalCertificate
import com.armyenjoyers.hospital.repository.HospitalCertificateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/certificates")
class HospitalCertificatesController
@Autowired constructor(
    private val hospitalCertificateRepository: HospitalCertificateRepository
) {

    @GetMapping("/")
    fun getAll(): MutableList<HospitalCertificate> {
        return hospitalCertificateRepository.findAll()
    }

}