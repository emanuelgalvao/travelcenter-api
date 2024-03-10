package com.emanuelgalvao.travelcenter.app.controllers

import com.emanuelgalvao.travelcenter.app.dto.request.LoginRequestDTO
import com.emanuelgalvao.travelcenter.app.dto.request.RegisterRequestDTO
import com.emanuelgalvao.travelcenter.admin.dto.response.AuthenticationResponseDTO
import com.emanuelgalvao.travelcenter.shared.entities.User
import com.emanuelgalvao.travelcenter.shared.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.shared.repositories.UserRepository
import com.emanuelgalvao.travelcenter.shared.security.TokenService
import com.emanuelgalvao.travelcenter.shared.security.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/app")
class AppUserController {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var tokenService: TokenService

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): Any {
        return try {
            val userPassword = UsernamePasswordAuthenticationToken(
                loginRequestDTO.email,
                loginRequestDTO.password
            )
            val auth = authenticationManager.authenticate(userPassword)

            val user = auth.principal as? User

            user?.let {
                val token = tokenService.generateToken(it)
                val authenticationOutputDTO = AuthenticationResponseDTO(
                    id = it.id,
                    name = it.name,
                    email = it.email,
                    token = token
                )
                ResponseEntity.ok(authenticationOutputDTO)
            } ?: run {
                throw BadRequestException("Email e/ou senha inválidos.")
            }
        } catch (exception: Exception) {
            BadRequestException("Email e/ou senha inválidos.")
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody registerRequestDTO: RegisterRequestDTO): Any {
        return try {
            userRepository.findByEmail(registerRequestDTO.email)?.let {
                throw BadRequestException("Email já cadastrado.")
            }
            if (registerRequestDTO.password != registerRequestDTO.confirmPassword) {
                throw BadRequestException("Senhas são diferentes.")
            }
            val encryptedPassword = BCryptPasswordEncoder().encode(registerRequestDTO.password)
            val newUser = User(
                name = registerRequestDTO.name,
                email = registerRequestDTO.email,
                passwordHash = encryptedPassword,
                role = UserRole.DEFAULT_USER
            )
            userRepository.save(newUser)

            val userPassword = UsernamePasswordAuthenticationToken(
                registerRequestDTO.email,
                registerRequestDTO.password
            )
            val auth = authenticationManager.authenticate(userPassword)

            val user = auth.principal as? User

            user?.let {
                val token = tokenService.generateToken(it)
                val authenticationOutputDTO = AuthenticationResponseDTO(
                    id = it.id,
                    name = it.name,
                    email = it.email,
                    token = token
                )
                ResponseEntity.ok(authenticationOutputDTO)
            } ?: run {
                throw BadRequestException("Email e/ou senha inválidos.")
            }
        } catch (exception: Exception) {
            exception
        }
    }
}