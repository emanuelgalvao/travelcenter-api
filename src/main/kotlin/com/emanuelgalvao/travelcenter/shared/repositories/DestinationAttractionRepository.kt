package com.emanuelgalvao.travelcenter.shared.repositories

import com.emanuelgalvao.travelcenter.shared.entities.DestinationAttraction
import org.springframework.data.jpa.repository.JpaRepository

interface DestinationAttractionRepository: JpaRepository<DestinationAttraction, String> {

    fun findByDestinationId(destinationId: String): List<DestinationAttraction>
}