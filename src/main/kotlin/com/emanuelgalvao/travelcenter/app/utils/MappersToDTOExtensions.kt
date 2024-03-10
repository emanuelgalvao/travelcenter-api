package com.emanuelgalvao.travelcenter.app.utils

import com.emanuelgalvao.travelcenter.app.dto.response.DestinationDetailsAttractionResponseDTO
import com.emanuelgalvao.travelcenter.app.dto.response.DestinationDetailsRatingResponseDTO
import com.emanuelgalvao.travelcenter.app.dto.response.HomeSectionDestinationResponseDTO
import com.emanuelgalvao.travelcenter.app.dto.response.UserFavoriteResponseDTO
import com.emanuelgalvao.travelcenter.shared.entities.Destination
import com.emanuelgalvao.travelcenter.shared.entities.DestinationAttraction
import com.emanuelgalvao.travelcenter.shared.entities.DestinationRating
import com.emanuelgalvao.travelcenter.shared.entities.UserFavorite

fun DestinationAttraction.toResponseDTO(): DestinationDetailsAttractionResponseDTO =
    DestinationDetailsAttractionResponseDTO(
        id = this.id,
        name = this.name,
        longitude = this.longitude,
        latitude = this.latitude
    )

fun DestinationRating.toResponseDTO(): DestinationDetailsRatingResponseDTO =
    DestinationDetailsRatingResponseDTO(
        id = this.id,
        userName = this.user.name,
        description = this.description,
        rating = this.rating.toString()
    )

fun Destination.toResponseDTO(): HomeSectionDestinationResponseDTO =
    HomeSectionDestinationResponseDTO(
        id = this.id,
        name = this.name,
        photo = this.photoUrl,
        rate = this.rate.toString()
    )

fun UserFavorite.toResponseDTO(): UserFavoriteResponseDTO =
    UserFavoriteResponseDTO(
        id = this.id,
        destinationId = this.destination.id,
        destinationName = this.destination.name
    )