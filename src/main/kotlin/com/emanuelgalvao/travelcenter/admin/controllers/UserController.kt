package com.emanuelgalvao.travelcenter.admin.controllers

import com.emanuelgalvao.travelcenter.admin.dto.request.RegisterRequestDTO
import com.emanuelgalvao.travelcenter.admin.dto.response.UserResponseDTO
import com.emanuelgalvao.travelcenter.admin.utils.toResponseDTO
import com.emanuelgalvao.travelcenter.shared.entities.User
import com.emanuelgalvao.travelcenter.shared.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.shared.repositories.UserRepository
import com.emanuelgalvao.travelcenter.shared.security.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    lateinit var repository: UserRepository

    @GetMapping("/users")
    fun getAllUsers(): List<UserResponseDTO> {
        return repository.findAll().map { it.toResponseDTO() }
    }

    @PostMapping("/user")
    fun createUser(@RequestBody registerDTO: RegisterRequestDTO): Any {
        return try {
            repository.findByEmail(registerDTO.email)?.let {
                throw BadRequestException("Email já cadastrado.")
            }
            val encryptedPassword = BCryptPasswordEncoder().encode(registerDTO.password)
            val newUser = User(
                name = registerDTO.name,
                email = registerDTO.email,
                passwordHash = encryptedPassword,
                role = getUserRole(registerDTO.role)
            )
            repository.save(newUser).toResponseDTO()
        } catch (exception: Exception) {
            exception
        }
    }

    private fun getUserRole(role: String): UserRole {
        return when (role) {
            UserRole.ADMIN.roleName -> UserRole.ADMIN
            else -> UserRole.DEFAULT_USER
        }
    }
}