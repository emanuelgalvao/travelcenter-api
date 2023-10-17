package com.emanuelgalvao.travelcenter.entities

import jakarta.persistence.*

@Entity
@Table(name = "user_favorites")
data class UserFavorite(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @ManyToOne
    val user: User,
    @ManyToOne
    val destination: Destination
)
