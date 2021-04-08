package com.armyenjoyers.hospital.controller

import com.armyenjoyers.hospital.model.Role
import com.armyenjoyers.hospital.repository.RoleRepository
import com.armyenjoyers.hospital.model.Login
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    val roleRepository: RoleRepository
) {


    @GetMapping("/roles")
    fun roles(): ResponseEntity.BodyBuilder {
        println(roleRepository.findAll())
      return ResponseEntity.ok()
    }

    @PostMapping("/login")
    fun login(@RequestBody login: Login): ResponseEntity<Login> {
        return ResponseEntity.ok().body(login)
    }

}