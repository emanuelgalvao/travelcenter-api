package com.emanuelgalvao.travelcenter.controllers

import com.emanuelgalvao.travelcenter.dto.input.AuthenticationDTO
import com.emanuelgalvao.travelcenter.dto.output.AuthenticationOutputDTO
import com.emanuelgalvao.travelcenter.dto.output.UserOutputDTO
import com.emanuelgalvao.travelcenter.entities.User
import com.emanuelgalvao.travelcenter.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.security.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.BodyBuilder
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
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
    fun auth(@RequestBody authenticationDTO: AuthenticationDTO): Any {
        val userPassword = UsernamePasswordAuthenticationToken(
            authenticationDTO.email,
            authenticationDTO.password
        )
        return try {
            val auth = authenticationManager.authenticate(userPassword)

            val user = auth.principal as? User

            user?.let {
                val token = tokenService.generateToken(it)
                val authenticationOutputDTO = AuthenticationOutputDTO(
                    name = it.name,
                    email = it.email,
                    token = token
                )
                ResponseEntity.ok(authenticationOutputDTO)
            } ?: run {
                BadRequestException("Email e/ou senha inválidos.")
            }
        } catch (exception: Exception) {
            BadRequestException("Email e/ou senha inválidos.")
        }

    }
}