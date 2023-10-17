package com.emanuelgalvao.travelcenter.security

import com.emanuelgalvao.travelcenter.exceptions.RegisterNotFoundException
import com.emanuelgalvao.travelcenter.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorizationService: UserDetailsService {

    @Autowired
    lateinit var repository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        return username?.let {
            repository.findByEmail(it)
        } ?: throw RegisterNotFoundException("Usuário não encontrado.")
    }
}