package com.emanuelgalvao.travelcenter.shared.repositories

import com.emanuelgalvao.travelcenter.shared.entities.Country
import com.emanuelgalvao.travelcenter.shared.entities.Destination
import org.springframework.data.jpa.repository.JpaRepository

interface DestinationRepository: JpaRepository<Destination, String> {

    fun findByNameContains(name: String): List<Destination>

    fun findTop5ByOrderByRate(): List<Destination>

    fun findTop5ByCountry(country: Country): List<Destination>

    fun findByTypeId(id: String): List<Destination>
}