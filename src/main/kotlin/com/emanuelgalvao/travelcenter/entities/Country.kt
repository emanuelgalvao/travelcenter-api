package com.emanuelgalvao.travelcenter.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.boot.context.properties.bind.DefaultValue

@Entity
@Table(name = "countries")
data class Country(
    @Id
    val iso: String,
    val name: String
)
