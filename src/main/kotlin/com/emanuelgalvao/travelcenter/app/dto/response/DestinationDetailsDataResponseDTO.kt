package com.emanuelgalvao.travelcenter.app.dto.response

data class DestinationDetailsDataResponseDTO(
    val destinationInfo: DestinationDetailsInfoResponseDTO,
    val destinationAttractions: List<DestinationDetailsAttractionResponseDTO>,
    val ratings: List<DestinationDetailsRatingResponseDTO>
)
