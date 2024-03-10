package com.emanuelgalvao.travelcenter.shared.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

data class RegisterNotFoundException(
    override val message: String
): ResponseStatusException(
    HttpStatus.NOT_FOUND,
    message
)
