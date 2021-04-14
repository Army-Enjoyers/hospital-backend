package com.armyenjoyers.hospital.domain

import javax.persistence.*

@MappedSuperclass
data class BaseTypeEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    var id: Int?,

    @Column(name="name")
    val name: String
)