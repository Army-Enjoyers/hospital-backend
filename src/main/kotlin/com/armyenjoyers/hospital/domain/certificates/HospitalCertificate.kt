package com.armyenjoyers.hospital.domain.certificates

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document("hospital_certificates")
data class HospitalCertificate(
    @Id
    var id: String?,
    val number: String,
    val issuedAt: LocalDateTime,
    val causeStartDate: LocalDate,
    val releaseStart: LocalDateTime,
    val releaseEnd: LocalDateTime,
    val causeCode: Int,
    val purpose: String,
    val note: String,

    @Indexed
    val patientId: String,
    @Indexed
    val doctorId: String
)