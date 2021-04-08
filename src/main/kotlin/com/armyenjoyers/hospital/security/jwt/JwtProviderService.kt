package com.armyenjoyers.hospital.security.jwt

import com.armyenjoyers.hospital.model.Role
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class JwtProviderService {

    fun createToken(username: String, roles: List<Role>): String{

    }

    fun getAuthentication(token: String): Authentication{

    }

    fun getUserName(token: String): String{

    }

    fun validateToken(token: String): Boolean{

    }

    fun getRoleNames(personnelRoles: List<Role>): List<String>{
        return personnelRoles.map{role -> role.name}
    }

}