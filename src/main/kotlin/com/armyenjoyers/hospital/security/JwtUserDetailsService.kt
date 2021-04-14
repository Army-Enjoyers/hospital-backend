package com.armyenjoyers.hospital.security

import com.armyenjoyers.hospital.security.jwt.JwtUserFactory
import com.armyenjoyers.hospital.service.HospitalPersonnelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.bind.Name
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JwtUserDetailsService
@Autowired constructor(
    private val hospitalPersonnelService: HospitalPersonnelService,
    private val jwtUserFactory: JwtUserFactory
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails? {
        val personnel = hospitalPersonnelService.findByUsername(username)
        return personnel?.let { jwtUserFactory.create(it) }
    }
}