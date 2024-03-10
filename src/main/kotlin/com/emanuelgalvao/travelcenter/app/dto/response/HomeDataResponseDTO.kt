package com.emanuelgalvao.travelcenter.app.dto.response

import com.emanuelgalvao.travelcenter.shared.entities.DestinationType

data class HomeDataResponseDTO(
    val tip: String,
    val destinationTypes: List<DestinationType>,
    val sections: List<HomeSectionResponseDTO>
)
