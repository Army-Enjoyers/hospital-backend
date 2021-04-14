package com.armyenjoyers.hospital.domain.requests

import org.springframework.data.annotation.Id
import java.time.LocalDate

data class MedicalRequest(
    @Id
    var id: String?,
    val requestDate: LocalDate,
    var homeVisit: Boolean,
    var plannedHospitalization: Boolean,
    var treatmentReferral: String?,
    var paidService: PaidService?,
    var requestType: RequestType,
    var medicalBoardRequest: MedicalBoardRequest?,

    var objectiveStatuses: MutableList<ObjectiveStatus>,
    var diseases: MutableList<Disease>,

    val patientId: String,
    val doctorId: String
)

enum class RequestType {
    PAID,
    SCHEDULED
}
