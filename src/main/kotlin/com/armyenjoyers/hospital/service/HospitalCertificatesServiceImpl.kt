package com.armyenjoyers.hospital.service

import com.armyenjoyers.hospital.domain.certificates.HospitalCertificate
import com.armyenjoyers.hospital.repository.HospitalCertificateRepository
import com.armyenjoyers.hospital.service.exception.HospitalCertificateNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HospitalCertificatesServiceImpl
@Autowired constructor(
    val hospitalCertificatesRepository: HospitalCertificateRepository
) : HospitalCertificatesService {

    override fun get(id: String): HospitalCertificate {
        return hospitalCertificatesRepository.findById(id)
            .orElseThrow { HospitalCertificateNotFoundException(id) }
    }

    override fun getAll(): List<HospitalCertificate> {
        return hospitalCertificatesRepository.findAll()
    }

    override fun save(hospitalCertificate: HospitalCertificate): HospitalCertificate {
        hospitalCertificate.id = null
        return hospitalCertificatesRepository.save(hospitalCertificate)
    }

    override fun update(id: String, hospitalCertificate: HospitalCertificate): HospitalCertificate {
        hospitalCertificate.id = id
        return hospitalCertificatesRepository.save(hospitalCertificate)
    }
}