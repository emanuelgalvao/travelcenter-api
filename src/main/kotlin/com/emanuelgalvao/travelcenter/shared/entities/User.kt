package com.emanuelgalvao.travelcenter.shared.entities

import com.emanuelgalvao.travelcenter.shared.security.UserRole
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    val name: String,
    val email: String,
    val passwordHash: String,
    val role: UserRole
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val roles: MutableList<GrantedAuthority> = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
        if (role == UserRole.ADMIN) {
            roles.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
        return roles
    }

    override fun getPassword(): String = passwordHash

    override fun getUsername(): String = name

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}
