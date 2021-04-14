package com.armyenjoyers.hospital.security.exception

class JwtUsernameNotFoundException(
    val username: String
): Exception()