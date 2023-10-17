package com.emanuelgalvao.travelcenter.dto.input

data class RegisterDTO(
    val name: String,
    val email: String,
    val password: String,
    val role: String = ""
)