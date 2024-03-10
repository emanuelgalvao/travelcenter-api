package com.emanuelgalvao.travelcenter.shared.entities

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
