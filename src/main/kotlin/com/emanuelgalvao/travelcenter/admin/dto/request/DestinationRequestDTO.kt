package com.emanuelgalvao.travelcenter.admin.dto.request

data class DestinationRequestDTO(
    val name: String,
    val description: String,
    val photoUrl: String,
    val countryIso: String,
    val typeId: String,
    val rate: Float
)
