package com.emanuelgalvao.travelcenter.dto.input

data class DestinationInputDTO(
    val name: String,
    val description: String,
    val photoUrl: String,
    val countryIso: String,
    val typeId: String,
    val rate: Float
)
