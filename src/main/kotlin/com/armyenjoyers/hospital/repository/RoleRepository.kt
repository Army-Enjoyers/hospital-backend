package com.armyenjoyers.hospital.repository

import com.armyenjoyers.hospital.domain.personnel.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Int>{
    fun findByName(name: String): Role
}