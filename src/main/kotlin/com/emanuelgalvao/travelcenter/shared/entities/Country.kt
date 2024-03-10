package com.emanuelgalvao.travelcenter.shared.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "countries")
data class Country(
    @Id
    val iso: String,
    val name: String
)
