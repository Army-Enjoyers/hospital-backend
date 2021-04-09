package com.armyenjoyers.hospital.controller

import com.armyenjoyers.hospital.dto.AuthenticationRequestDto
import com.armyenjoyers.hospital.dto.AuthenticationResponseDto
import com.armyenjoyers.hospital.dto.RegistrationRequestDto
import com.armyenjoyers.hospital.security.exception.JwtAuthenticationException
import com.armyenjoyers.hospital.security.jwt.JwtProviderService
import com.armyenjoyers.hospital.service.HospitalPersonnelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController
@Autowired constructor(
    private val authenticationManager: AuthenticationManager,
    private val jwtProviderService: JwtProviderService,
    private val hospitalPersonnelService: HospitalPersonnelService
) {

    @PostMapping("/login")
    fun login(@RequestBody authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<Any?> {
        try{
            val username = authenticationRequestDto.username
            val password = authenticationRequestDto.password
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            val hospitalPersonnel = hospitalPersonnelService.findByUsername(username)
                ?: throw UsernameNotFoundException("User with such username not found")
            val token = jwtProviderService.createToken(username, hospitalPersonnel.roles)
            return ResponseEntity.ok(AuthenticationResponseDto(username, token))
        } catch(ex: JwtAuthenticationException){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid login or password")
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody registrationRequestDto: RegistrationRequestDto): ResponseEntity<String> {
        hospitalPersonnelService.register(registrationRequestDto)
        return ResponseEntity.ok("registered")
    }

}