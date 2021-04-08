package com.armyenjoyers.hospital.security

import com.armyenjoyers.hospital.security.jwt.JwtUserFactory
import com.armyenjoyers.hospital.service.HospitalPersonnelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailService @Autowired constructor(
    private val hospitalPersonnelService: HospitalPersonnelService,
    private val jwtUserFactory: JwtUserFactory
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val personnel = hospitalPersonnelService.findByUsername(username) ?: throw UsernameNotFoundException(username)
        return jwtUserFactory.create(personnel)
    }
}