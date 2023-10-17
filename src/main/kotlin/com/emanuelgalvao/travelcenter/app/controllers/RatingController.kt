package com.emanuelgalvao.travelcenter.app.controllers

import com.emanuelgalvao.travelcenter.app.dto.request.RatingRequestDTO
import com.emanuelgalvao.travelcenter.entities.Destination
import com.emanuelgalvao.travelcenter.entities.DestinationRating
import com.emanuelgalvao.travelcenter.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.repositories.DestinationRatingRepository
import com.emanuelgalvao.travelcenter.repositories.DestinationRepository
import com.emanuelgalvao.travelcenter.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/app")
class RatingController {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var destinationRepository: DestinationRepository

    @Autowired
    lateinit var ratingRepository: DestinationRatingRepository

    @PostMapping("/destinationRating")
    fun createDestinationRating(@RequestBody ratingRequestDTO: RatingRequestDTO): DestinationRating {

        val user = userRepository.findById(ratingRequestDTO.userId).orElseThrow {
            BadRequestException("Usuário inválido.")
        }

        val destination = destinationRepository.findById(ratingRequestDTO.destinationId).orElseThrow {
            BadRequestException("Destino inválido.")
        }

        val rating = DestinationRating(
            destination = destination,
            user = user,
            description = ratingRequestDTO.ratingText,
            rating = ratingRequestDTO.rate
        )

        return ratingRepository.save(rating)
    }
}