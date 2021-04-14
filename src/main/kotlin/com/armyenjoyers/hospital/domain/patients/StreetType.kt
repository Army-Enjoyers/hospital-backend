package com.armyenjoyers.hospital.domain.patients

import com.armyenjoyers.hospital.domain.BaseTypeEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "street_types")
class StreetType(id: Int?, name: String) : BaseTypeEntity(id, name)