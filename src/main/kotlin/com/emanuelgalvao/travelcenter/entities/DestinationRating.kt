package com.emanuelgalvao.travelcenter.entities

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
