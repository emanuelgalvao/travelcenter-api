package com.emanuelgalvao.travelcenter.shared.security

import com.emanuelgalvao.travelcenter.shared.entities.User
import com.emanuelgalvao.travelcenter.shared.exceptions.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {

    @Autowired
    private lateinit var jwtEncoder: JwtEncoder

    @Autowired
    private lateinit var jwtDecoder: JwtDecoder

    fun generateToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(getExpirationDate())
            .subject(user.email)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun validateToken(token: String): String {
        return try {
            val jwt = jwtDecoder.decode(token)
            jwt.subject
        } catch (e: Exception) {
            throw BadRequestException("Token inválido.")
        }
    }

    private fun getExpirationDate(): Instant {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }
}