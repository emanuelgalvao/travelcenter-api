package com.emanuelgalvao.travelcenter.repositories

import com.emanuelgalvao.travelcenter.entities.Country
import com.emanuelgalvao.travelcenter.entities.Destination
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DestinationRepository: JpaRepository<Destination, String> {

    fun findByNameContains(name: String): List<Destination>

    fun findTop5ByOrderByRate(): List<Destination>

    fun findTop5ByCountry(country: Country): List<Destination>

    fun findByTypeId(id: String): List<Destination>
}