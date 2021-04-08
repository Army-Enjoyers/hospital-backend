package com.armyenjoyers.hospital.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "permissions")
data class Permission (

    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name")
    val name:String
)