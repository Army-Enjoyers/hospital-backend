package com.armyenjoyers.hospital.domain.patients

import javax.persistence.*

@Entity
@Table(name = "life_anamnesis")
class LifeAnamnesis(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int?,

    @Column(name = "value")
    val value: String
)