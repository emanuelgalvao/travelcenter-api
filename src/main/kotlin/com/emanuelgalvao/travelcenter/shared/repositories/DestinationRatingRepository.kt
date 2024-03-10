package com.emanuelgalvao.travelcenter.shared.repositories

import com.emanuelgalvao.travelcenter.shared.entities.DestinationRating
import org.springframework.data.jpa.repository.JpaRepository

interface DestinationRatingRepository: JpaRepository<DestinationRating, String> {

    fun findByDestinationId(id: String): List<DestinationRating>
}