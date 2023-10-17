package com.emanuelgalvao.travelcenter.entities

import jakarta.persistence.*

@Entity
@Table(name = "destination_attractions")
data class DestinationAttraction(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    @ManyToOne
    val destination: Destination,
    val name: String,
    val longitude: Double,
    val latitude: Double
)
