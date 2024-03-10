package com.emanuelgalvao.travelcenter.app.dto.response

data class HomeSectionResponseDTO(
    val name: String,
    val destinations: List<HomeSectionDestinationResponseDTO>
)