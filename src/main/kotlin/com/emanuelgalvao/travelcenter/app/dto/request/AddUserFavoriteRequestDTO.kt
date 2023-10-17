package com.emanuelgalvao.travelcenter.app.dto.request

data class AddUserFavoriteRequestDTO(
    val userId: String,
    val destinationId: String
)
