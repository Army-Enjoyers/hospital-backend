package com.armyenjoyers.hospital.security

import com.armyenjoyers.hospital.repository.HospitalPersonnelRepository
import com.armyenjoyers.hospital.security.jwt.JwtUserFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JwtUserDetailsService
@Autowired constructor(
    private val hospitalPersonnelRepository: HospitalPersonnelRepository,
    private val jwtUserFactory: JwtUserFactory
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails? {
        val personnel = hospitalPersonnelRepository.findByUsername(username)
        return personnel?.let { jwtUserFactory.create(it) }
    }
}