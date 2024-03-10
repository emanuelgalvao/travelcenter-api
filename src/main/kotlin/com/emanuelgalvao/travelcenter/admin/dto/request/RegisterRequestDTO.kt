package com.emanuelgalvao.travelcenter.admin.dto.request

data class RegisterRequestDTO(
    val name: String,
    val email: String,
    val password: String,
    val role: String = ""
)