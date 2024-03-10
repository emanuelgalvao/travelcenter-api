package com.emanuelgalvao.travelcenter.app.controllers

import com.emanuelgalvao.travelcenter.app.dto.response.DestinationDetailsDataResponseDTO
import com.emanuelgalvao.travelcenter.app.dto.response.DestinationDetailsInfoResponseDTO
import com.emanuelgalvao.travelcenter.app.utils.toResponseDTO
import com.emanuelgalvao.travelcenter.shared.exceptions.RegisterNotFoundException
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationAttractionRepository
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationRatingRepository
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationRepository
import com.emanuelgalvao.travelcenter.shared.repositories.UserFavoriteRepository
import com.emanuelgalvao.travelcenter.shared.security.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/app")
class DestinationDetailsController {

    @Autowired
    lateinit var destinationRepository: DestinationRepository

    @Autowired
    lateinit var destinationAttractionRepository: DestinationAttractionRepository

    @Autowired
    lateinit var ratingRepository: DestinationRatingRepository

    @Autowired
    lateinit var favoriteRepository: UserFavoriteRepository

    @Autowired
    lateinit var tokenService: TokenService

    @GetMapping("/destinationDetails/{id}")
    fun getDestinationDetails(@RequestHeader headers: Map<String, String>, @PathVariable("id") id: String): Any {
        return try {
            val destination = destinationRepository.findById(id).orElseThrow {
                RegisterNotFoundException("Destino não encontrado.")
            }

            val attractions = destinationAttractionRepository.findByDestinationId(id).map {
                it.toResponseDTO()
            }

            val ratings = ratingRepository.findByDestinationId(id).map {
                it.toResponseDTO()
            }

            var favoriteId = ""

            tokenService.getUserIdFromHeaders(headers)?.let {
                val favorite = favoriteRepository.findByUserIdAndDestinationId(it, id)
                favoriteId = favorite.firstOrNull()?.id ?: ""
            }

            DestinationDetailsDataResponseDTO(
                destinationInfo = DestinationDetailsInfoResponseDTO(
                    name = destination.name,
                    photoUrl = destination.photoUrl,
                    description = destination.description,
                    favoriteId = favoriteId,
                    rate = destination.rate.toString()
                ),
                destinationAttractions = attractions,
                ratings = ratings
            )
        } catch (exception: Exception) {
            exception
        }
    }
}