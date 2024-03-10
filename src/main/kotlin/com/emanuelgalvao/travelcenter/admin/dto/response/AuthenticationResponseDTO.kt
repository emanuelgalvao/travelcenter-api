package com.emanuelgalvao.travelcenter.admin.dto.response

data class AuthenticationResponseDTO (
    val id: String,
    val name: String,
    val email: String,
    val token: String
)