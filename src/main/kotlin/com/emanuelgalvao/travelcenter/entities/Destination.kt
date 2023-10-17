package com.emanuelgalvao.travelcenter.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "destinations")
data class Destination(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    val name: String,
    val description: String,
    val photoUrl: String,
    val rate: Float,
    @ManyToOne
    val country: Country,
    @ManyToOne
    val type: DestinationType
)
