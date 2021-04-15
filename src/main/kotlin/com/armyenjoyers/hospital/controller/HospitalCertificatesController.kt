package com.armyenjoyers.hospital.controller

import com.armyenjoyers.hospital.domain.certificates.HospitalCertificate
import com.armyenjoyers.hospital.repository.HospitalCertificateRepository
import com.armyenjoyers.hospital.service.HospitalCertificatesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/certificates")
class HospitalCertificatesController
@Autowired constructor(
    private val hospitalCertificatesService: HospitalCertificatesService
) {

    @GetMapping("/")
    fun getAll(): List<HospitalCertificate> {
        return hospitalCertificatesService.getAll()
    }

    @PostMapping("/")
    fun save(@RequestBody hospitalCertificate: HospitalCertificate): HospitalCertificate {
        return hospitalCertificatesService.save(hospitalCertificate)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): HospitalCertificate {
        return hospitalCertificatesService.get(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody hospitalCertificate: HospitalCertificate): HospitalCertificate {
        return hospitalCertificatesService.update(id, hospitalCertificate)
    }

}