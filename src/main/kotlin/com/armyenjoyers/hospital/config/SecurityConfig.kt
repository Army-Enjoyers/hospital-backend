package com.armyenjoyers.hospital.config

import com.armyenjoyers.hospital.security.jwt.JwtConfigurer
import com.armyenjoyers.hospital.security.jwt.JwtProviderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
class SecurityConfig
@Autowired constructor(
    private val jwtProviderService: JwtProviderService
): WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        http?.let{
            it.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/api/**").hasAnyAuthority("user:get:*", "user:write:*")
                .antMatchers("/**", ).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(JwtConfigurer(jwtProviderService))
        }
    }
}