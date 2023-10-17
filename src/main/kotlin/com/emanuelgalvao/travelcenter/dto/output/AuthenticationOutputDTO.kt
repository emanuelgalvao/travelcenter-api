package com.emanuelgalvao.travelcenter.dto.output

data class AuthenticationOutputDTO (
    val name: String,
    val email: String,
    val token: String
)