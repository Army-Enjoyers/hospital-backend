package com.armyenjoyers.hospital.domain.patients

import com.armyenjoyers.hospital.domain.BaseTypeEntity
import javax.persistence.*

@Entity
@Table(name = "placement_types")
class PlacementType(id: Int?, name: String) : BaseTypeEntity(id, name)