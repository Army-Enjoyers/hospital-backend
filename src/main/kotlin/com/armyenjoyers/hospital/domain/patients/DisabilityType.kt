package com.armyenjoyers.hospital.domain.patients

import com.armyenjoyers.hospital.domain.BaseTypeEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "disability_types")
class DisabilityType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    var id: Int?,

    @Column(name="name")
    val name: String,

    @set:JsonIgnore
    @OneToMany(mappedBy = "disabilityType", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var patientDisabilities: MutableList<PatientDisability>?
): Serializable