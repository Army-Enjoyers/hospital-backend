package com.armyenjoyers.hospital.security.jwt

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


class JwtConfigurer
constructor(
    private val jwtProviderService: JwtProviderService,
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity?) {
        val jwtFilter = JwtFilter(jwtProviderService)
        builder?.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}