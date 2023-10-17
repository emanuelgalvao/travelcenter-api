package com.emanuelgalvao.travelcenter.app.dto

import com.emanuelgalvao.travelcenter.entities.DestinationType

data class HomeDataDTO(
    val destinationTypes: List<DestinationType>,
    val sections: List<HomeSectionDTO>
)
