package com.emanuelgalvao.travelcenter.app.dto.request

data class RatingRequestDTO(
    val userId: String,
    val destinationId: String,
    val ratingText: String,
    val rate: Int
)