package com.emanuelgalvao.travelcenter.app.dto

import com.emanuelgalvao.travelcenter.entities.DestinationAttraction
import com.emanuelgalvao.travelcenter.entities.DestinationRating

data class DestinationDetailsDataDTO(
    val destinationInfo: DestinationDetailsInfoDTO,
    val destinationAttractions: List<DestinationAttraction>,
    val ratings: List<DestinationRating>
)
