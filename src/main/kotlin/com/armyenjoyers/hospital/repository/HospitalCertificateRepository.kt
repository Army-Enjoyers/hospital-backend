package com.armyenjoyers.hospital.repository

import com.armyenjoyers.hospital.domain.certificates.HospitalCertificate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface HospitalCertificateRepository: MongoRepository<HospitalCertificate, String> {
}