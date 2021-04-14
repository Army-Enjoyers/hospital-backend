package com.armyenjoyers.hospital.domain.patients

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class PatientDisabilityId(
    var patientsId: Int,
    var disabilityType: DisabilityType
) : Serializable