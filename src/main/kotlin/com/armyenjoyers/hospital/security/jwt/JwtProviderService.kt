package com.armyenjoyers.hospital.security.jwt

import com.armyenjoyers.hospital.domain.Role
import com.armyenjoyers.hospital.security.JwtUserDetailsService
import com.armyenjoyers.hospital.security.exception.JwtAuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.Integer.min
import java.security.Key
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Service
class JwtProviderService
@Autowired constructor(
    private val userDetailsService: JwtUserDetailsService
) {

    @Value("\${jwt.secret}")
    lateinit var secret: String

    @Value("\${jwt.expired}")
    var expirationPeriod: Int = DEFAULT_TOKEN_LIFETIME

    private lateinit var key: Key

    private val signatureAlgorithm = SignatureAlgorithm.HS256

    @PostConstruct
    fun init() {
        key = Keys.secretKeyFor(signatureAlgorithm)
    }

    fun createToken(username: String, roles: List<Role>): String {
        val claims: Claims = Jwts.claims().setSubject(username)
        claims.put("permissions", roles.flatMap { it.permissions })

        val now = Date()
        val expiration = Date(now.time + expirationPeriod)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(key, signatureAlgorithm)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUserName(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUserName(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token).body.subject
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX))
            bearerToken.substring(TOKEN_PREFIX.length)
        else
            null
    }


    fun validateToken(token: String): Boolean {
        try {
            val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return claims.body.expiration.before(Date())
        } catch (ex: JwtException) {
            throw JwtAuthenticationException();
        }
    }

    fun getRoleNames(personnelRoles: List<Role>): List<String> {
        return personnelRoles.map { role -> role.name }
    }

    companion object {
        const val DEFAULT_TOKEN_LIFETIME: Int = 3_600_000
        const val TOKEN_PREFIX = "Bearer_"
    }

}