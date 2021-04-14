package com.armyenjoyers.hospital.domain.patients

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "patients")
data class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    var lastName: String,
    var firstName: String,
    var patronymic: String,
    var birthDate: LocalDateTime,
    var gender: String,
    var citizenship: String,
    var job: String?,
    var polyclinic: String,
    var pensionerId: String?,
    var outpatientCardNumber: String?,
    var additionalInfo: String?,

    @OneToMany(mappedBy = "patient")
    val disabilities: MutableList<PatientDisability>,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "contingents_id", nullable = false)
    var contingent: Contingent,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "life_anamnesis_id", nullable = false)
    var lifeAnamnesis: LifeAnamnesis?,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "military_affiliations_id", nullable = false)
    var militaryAffiliation: MilitaryAffiliation?
)
