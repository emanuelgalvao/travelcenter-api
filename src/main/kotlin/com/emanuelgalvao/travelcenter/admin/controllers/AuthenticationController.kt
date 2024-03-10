package com.emanuelgalvao.travelcenter.admin.controllers

import com.emanuelgalvao.travelcenter.admin.dto.request.AuthenticationRequestDTO
import com.emanuelgalvao.travelcenter.admin.dto.response.AuthenticationResponseDTO
import com.emanuelgalvao.travelcenter.admin.utils.toAuthenticationResponseDTO
import com.emanuelgalvao.travelcenter.shared.entities.User
import com.emanuelgalvao.travelcenter.shared.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.shared.security.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var tokenService: TokenService

    @PostMapping("/auth")
    fun auth(@RequestBody authenticationDTO: AuthenticationRequestDTO): Any {
        val userPassword = UsernamePasswordAuthenticationToken(
            authenticationDTO.email,
            authenticationDTO.password
        )
        return try {
            val auth = authenticationManager.authenticate(userPassword)

            val user = auth.principal as? User

            user?.let {
                val token = tokenService.generateToken(it)
                ResponseEntity.ok(it.toAuthenticationResponseDTO(token))
            } ?: run {
                BadRequestException("Email e/ou senha inválidos.")
            }
        } catch (exception: Exception) {
            BadRequestException("Email e/ou senha inválidos.")
        }
    }
}