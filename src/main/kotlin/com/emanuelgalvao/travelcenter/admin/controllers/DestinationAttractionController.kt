package com.emanuelgalvao.travelcenter.admin.controllers

import com.emanuelgalvao.travelcenter.admin.dto.request.DestinationAttractionRequestDTO
import com.emanuelgalvao.travelcenter.admin.dto.response.DestinationAttractionResponseDTO
import com.emanuelgalvao.travelcenter.admin.utils.toResponseDTO
import com.emanuelgalvao.travelcenter.shared.entities.DestinationAttraction
import com.emanuelgalvao.travelcenter.shared.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.shared.exceptions.RegisterNotFoundException
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationAttractionRepository
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kotlin.math.abs

@RestController
class DestinationAttractionController {

    @Autowired
    lateinit var repository: DestinationAttractionRepository

    @Autowired
    lateinit var destinationRepository: DestinationRepository

    @GetMapping("/destinationAttractions")
    fun getAllDestinationAttractions(): List<DestinationAttractionResponseDTO> {
        return repository.findAll().map {
            it.toResponseDTO()
        }
    }

    @GetMapping("/destinationAttraction/{id}")
    fun getDestinationAttraction(@PathVariable("id") id: String): Any {
        return try {
            repository.findById(id).map { it.toResponseDTO() }.orElseThrow {
                RegisterNotFoundException("Ponto turístico não encontrado.")
            }
        } catch (exception: Exception) {
            exception
        }
    }

    @PostMapping("/destinationAttraction")
    fun createDestinationAttraction(@RequestBody destinationAttractionRequestDTO: DestinationAttractionRequestDTO): Any {
        return try {
            val destinationAttraction = createDestinationAttractionFromDTO(destinationAttractionRequestDTO)
            repository.save(destinationAttraction).toResponseDTO()
        } catch (exception: Exception) {
            exception
        }
    }

    @PutMapping("/destinationAttraction/{id}")
    fun updateDestinationAttraction(
        @PathVariable("id") id: String,
        @RequestBody destinationAttractionRequestDTO: DestinationAttractionRequestDTO
    ): Any {
        return try {
            val destinationAttraction = repository.findById(id).orElseThrow {
                RegisterNotFoundException("Ponto turístico não encontrado.")
            }

            repository.save(
                createDestinationAttractionFromDTO(
                    destinationAttractionRequestDTO,
                    destinationAttraction.id
                )
            ).toResponseDTO()
        } catch (exception: Exception) {
            exception
        }
    }

    @DeleteMapping("/destinationAttraction/{id}")
    fun deleteDestinationAttraction(@PathVariable("id") id: String): Any {
        return try {
            val destinationAttraction = repository.findById(id).orElseThrow {
                RegisterNotFoundException("Ponto turístico não encontrado.")
            }
            repository.delete(destinationAttraction)
        } catch (exception: Exception) {
            exception
        }
    }

    private fun createDestinationAttractionFromDTO(
        destinationAttractionInputDTO: DestinationAttractionRequestDTO,
        destinationAttractionId: String = ""
    ): DestinationAttraction {
        val destination = destinationRepository.findById(destinationAttractionInputDTO.destinationId).orElseThrow {
            BadRequestException("Destino inválido.")
        }
        validateLongitudeAndLatitude(
            longitude = destinationAttractionInputDTO.longitude,
            latitude = destinationAttractionInputDTO.latitude
        )
        return DestinationAttraction(
            id = destinationAttractionId,
            destination = destination,
            name = destinationAttractionInputDTO.name,
            longitude = destinationAttractionInputDTO.longitude,
            latitude = destinationAttractionInputDTO.latitude
        )
    }

    private fun validateLongitudeAndLatitude(
        longitude: Double,
        latitude: Double
    ) {
        if (abs(latitude) > 90 || abs(latitude) < 0) {
            throw BadRequestException("Latitude inválida.")
        } else if (abs(longitude) > 180 || abs(longitude) < 0) {
            throw BadRequestException("Longitude inválida.")
        }
    }
}