package com.armyenjoyers.hospital.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class AuthController{

    @GetMapping
    fun getToken(): ResponseEntity<String> {
        return ResponseEntity.ok("Token")
    }

}