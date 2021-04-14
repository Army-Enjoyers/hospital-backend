package com.armyenjoyers.hospital.service

import com.armyenjoyers.hospital.domain.personnel.HospitalPersonnel
import com.armyenjoyers.hospital.domain.personnel.Role
import com.armyenjoyers.hospital.dto.RegistrationRequestDto
import com.armyenjoyers.hospital.repository.HospitalPersonnelRepository
import com.armyenjoyers.hospital.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class HospitalPersonnelServiceImpl @Autowired constructor(
    private val hospitalPersonnelRepository: HospitalPersonnelRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : HospitalPersonnelService {

    override fun register(personnel: HospitalPersonnel): HospitalPersonnel {
        val role: Role = roleRepository.findByName("USER")
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
        return hospitalPersonnelRepository.findByLogin(username)
    }

    override fun findById(id: Int): HospitalPersonnel? {
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
            mutableListOf<Role>()
        )
        register(personnel)
    }
}