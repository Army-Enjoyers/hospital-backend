package com.armyenjoyers.hospital.domain

import javax.persistence.*

@Entity
@Table(name = "token")
data class Token(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(name = "value")
    val value: String,

    @Column(name = "hospital_personnel_id")
    val hospitalPersonnelId: Int
)