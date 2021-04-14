package com.armyenjoyers.hospital.service

import com.armyenjoyers.hospital.domain.personnel.HospitalPersonnel
import com.armyenjoyers.hospital.domain.personnel.Role
import com.armyenjoyers.hospital.dto.RegistrationRequestDto
import com.armyenjoyers.hospital.repository.HospitalPersonnelRepository
import com.armyenjoyers.hospital.security.jwt.JwtProviderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class HospitalPersonnelServiceImpl @Autowired constructor(
    private val hospitalPersonnelRepository: HospitalPersonnelRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProviderService: JwtProviderService
) : HospitalPersonnelService {

    override fun register(personnel: HospitalPersonnel): HospitalPersonnel {
        val role: Role = Role.USER
        val personnelRoles = listOf(role)

        personnel.password = passwordEncoder.encode(personnel.password)
        personnel.roles = personnelRoles.toMutableList()

        hospitalPersonnelRepository.save(personnel)

        return personnel
    }

    override fun findAll(): List<HospitalPersonnel> {
        return hospitalPersonnelRepository.findAll()
    }

    override fun findByUsername(username: String?): HospitalPersonnel? {
        return username?.let { hospitalPersonnelRepository.findByUsername(it) }
    }

    override fun findById(id: String): HospitalPersonnel? {
        return hospitalPersonnelRepository.findById(id).orElse(null)
    }

    override fun register(registrationRequestDto: RegistrationRequestDto) {
        val personnel = HospitalPersonnel(
            null,
            registrationRequestDto.firstName,
            registrationRequestDto.lastName,
            registrationRequestDto.patronymic,
            registrationRequestDto.username,
            registrationRequestDto.password,
            "position?",
            mutableListOf<Role>(),
            jwtProviderService.createToken(registrationRequestDto.username, listOf(Role.USER))
        )
        register(personnel)
    }
}