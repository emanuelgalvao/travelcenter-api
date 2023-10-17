package com.emanuelgalvao.travelcenter.repositories

import com.emanuelgalvao.travelcenter.entities.Destination
import com.emanuelgalvao.travelcenter.entities.DestinationAttraction
import org.springframework.data.jpa.repository.JpaRepository

interface DestinationAttractionRepository: JpaRepository<DestinationAttraction, String> {

    fun findByDestinationId(destinationId: String): List<DestinationAttraction>
}