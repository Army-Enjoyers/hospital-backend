package com.armyenjoyers.hospital.service

import com.armyenjoyers.hospital.domain.certificates.HospitalCertificate
import com.armyenjoyers.hospital.domain.requests.MedicalRequest

interface MedicalRequestService {

    fun get(id: String): MedicalRequest
    fun getAll(): List<MedicalRequest>
    fun save(hospitalCertificate: MedicalRequest): MedicalRequest
    fun update(id: String, hospitalCertificate: MedicalRequest): MedicalRequest

}