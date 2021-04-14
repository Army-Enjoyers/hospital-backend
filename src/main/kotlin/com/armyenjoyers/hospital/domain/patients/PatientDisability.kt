package com.armyenjoyers.hospital.domain.patients

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "patients_disabilities")
data class PatientDisability (

    var startDate: LocalTime,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name="disability_types_id", referencedColumnName = "id")
    val disabilityType: DisabilityType,

    @Id
    @ManyToOne
    @set:JsonIgnore
    @JoinColumn(name="patients_id", nullable = false, insertable = false, updatable = false)
    var patient: Patient?
): Serializable
