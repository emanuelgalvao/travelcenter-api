package com.emanuelgalvao.travelcenter.shared.entities

import jakarta.persistence.*

@Entity
@Table(name = "destination_rating")
data class DestinationRating(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @ManyToOne
    val destination: Destination,
    @ManyToOne
    val user: User,
    val description: String,
    val rating: Int
)
