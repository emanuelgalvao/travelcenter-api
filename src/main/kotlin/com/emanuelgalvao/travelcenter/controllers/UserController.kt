package com.emanuelgalvao.travelcenter.controllers

import com.emanuelgalvao.travelcenter.dto.input.RegisterDTO
import com.emanuelgalvao.travelcenter.dto.output.UserOutputDTO
import com.emanuelgalvao.travelcenter.entities.User
import com.emanuelgalvao.travelcenter.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.repositories.UserRepository
import com.emanuelgalvao.travelcenter.security.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.BodyBuilder
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
    fun getAllUsers(): List<UserOutputDTO> {
        return repository.findAll().map {
            UserOutputDTO(
                id = it.id,
                name = it.name,
                email = it.email,
                role = it.role.roleName
            )
        }
    }

    @PostMapping("/user")
    fun createUser(@RequestBody registerDTO: RegisterDTO): Any {
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
            repository.save(newUser)
            ResponseEntity.ok().build<Nothing>()
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