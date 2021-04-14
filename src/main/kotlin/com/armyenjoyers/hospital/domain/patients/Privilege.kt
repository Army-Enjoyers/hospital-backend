package com.armyenjoyers.hospital.domain.patients

import com.armyenjoyers.hospital.domain.BaseTypeEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "privileges")
class Privilege(id: Int?, name: String) : BaseTypeEntity(id, name)