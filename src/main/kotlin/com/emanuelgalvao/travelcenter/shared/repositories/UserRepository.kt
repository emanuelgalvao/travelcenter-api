package com.emanuelgalvao.travelcenter.shared.repositories

import com.emanuelgalvao.travelcenter.shared.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails

interface UserRepository: JpaRepository<User, String> {

    fun findByEmail(email: String): UserDetails?
}