package com.emanuelgalvao.travelcenter.shared.repositories

import com.emanuelgalvao.travelcenter.shared.entities.User
import com.emanuelgalvao.travelcenter.shared.security.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails

interface UserRepository: JpaRepository<User, String> {

    fun findByEmail(email: String): UserDetails?

    fun findByEmailAndRole(email: String, role: UserRole): User
}