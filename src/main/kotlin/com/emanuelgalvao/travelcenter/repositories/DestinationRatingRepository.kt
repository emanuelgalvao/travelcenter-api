package com.emanuelgalvao.travelcenter.repositories

import com.emanuelgalvao.travelcenter.entities.DestinationRating
import org.springframework.data.jpa.repository.JpaRepository

interface DestinationRatingRepository: JpaRepository<DestinationRating, String> {

    fun findByDestinationId(id: String): List<DestinationRating>
}