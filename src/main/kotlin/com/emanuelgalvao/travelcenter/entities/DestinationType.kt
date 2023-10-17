package com.emanuelgalvao.travelcenter.entities

import jakarta.persistence.*

@Entity
@Table(name = "destination_types")
data class DestinationType(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    var name: String,
    var iconUrl: String
)
