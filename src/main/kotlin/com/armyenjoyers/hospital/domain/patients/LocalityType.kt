package com.armyenjoyers.hospital.domain.patients

import com.armyenjoyers.hospital.domain.BaseTypeEntity
import javax.persistence.*

@Entity
@Table(name = "locality_type")
class LocalityType(id: Int?, name: String) : BaseTypeEntity(id, name)