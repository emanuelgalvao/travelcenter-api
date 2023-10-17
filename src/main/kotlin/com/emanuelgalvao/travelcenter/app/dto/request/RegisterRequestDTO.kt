package com.emanuelgalvao.travelcenter.app.dto.request

data class RegisterRequestDTO(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
