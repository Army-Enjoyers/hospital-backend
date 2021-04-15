package com.armyenjoyers.hospital.service

import com.armyenjoyers.hospital.domain.certificates.HospitalCertificate
import java.util.*

interface HospitalCertificatesService {

    fun get(id: String): HospitalCertificate
    fun getAll(): List<HospitalCertificate>
    fun save(hospitalCertificate: HospitalCertificate): HospitalCertificate
    fun update(id: String, hospitalCertificate: HospitalCertificate): HospitalCertificate

}