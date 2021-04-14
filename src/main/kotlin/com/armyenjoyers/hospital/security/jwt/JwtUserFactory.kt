package com.armyenjoyers.hospital.security.jwt

import com.armyenjoyers.hospital.domain.personnel.HospitalPersonnel
import com.armyenjoyers.hospital.domain.personnel.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service

@Service
class JwtUserFactory {

    fun create(personnel: HospitalPersonnel): JwtUser {
        return JwtUser(
            personnel.id!!,
            personnel.login,
            personnel.firstName,
            personnel.lastName,
            personnel.password,
            mapToGrantedAuthority(personnel.roles),
            personnel.password
        )
    }

    companion object {
        fun mapToGrantedAuthority(roles: List<Role>): MutableList<GrantedAuthority> {
            return roles.flatMap {it.permissions}.map { permission ->
                    SimpleGrantedAuthority(permission.name)
                }.toMutableList()
        }
    }

}