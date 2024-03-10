package com.emanuelgalvao.travelcenter.admin.utils

import com.emanuelgalvao.travelcenter.admin.dto.response.AuthenticationResponseDTO
import com.emanuelgalvao.travelcenter.admin.dto.response.DestinationAttractionResponseDTO
import com.emanuelgalvao.travelcenter.admin.dto.response.UserResponseDTO
import com.emanuelgalvao.travelcenter.shared.entities.DestinationAttraction
import com.emanuelgalvao.travelcenter.shared.entities.User

fun User.toAuthenticationResponseDTO(token: String): AuthenticationResponseDTO =
    AuthenticationResponseDTO(
        id = this.id,
        name = this.name,
        email = this.email,
        token = token
    )

fun DestinationAttraction.toResponseDTO(): DestinationAttractionResponseDTO =
    DestinationAttractionResponseDTO(
        id = this.id,
        name = this.name,
        longitude = this.longitude,
        latitude = this.latitude
    )

fun User.toResponseDTO(): UserResponseDTO =
    UserResponseDTO(
        id = this.id,
        name = this.name,
        email = this.email,
        role = this.role.roleName
    )