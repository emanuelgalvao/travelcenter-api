package com.emanuelgalvao.travelcenter.admin.dto.request

data class DestinationAttractionRequestDTO(
    val name: String,
    val destinationId: String,
    val longitude: Double,
    val latitude: Double
)
