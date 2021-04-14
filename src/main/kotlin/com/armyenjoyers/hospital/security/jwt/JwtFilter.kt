package com.armyenjoyers.hospital.security.jwt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class JwtFilter
@Autowired constructor(
    private val jwtProviderService: JwtProviderService
): GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val token: String? = jwtProviderService.resolveToken(request as HttpServletRequest)
        if(token != null && jwtProviderService.validateToken(token)){
            val authentication: Authentication? = jwtProviderService.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain?.doFilter(request, response)
    }
}