package com.emanuelgalvao.travelcenter.controllers

import com.emanuelgalvao.travelcenter.dto.input.DestinationInputDTO
import com.emanuelgalvao.travelcenter.entities.Destination
import com.emanuelgalvao.travelcenter.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.exceptions.RegisterNotFoundException
import com.emanuelgalvao.travelcenter.repositories.*
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class DestinationController {

    @Autowired
    lateinit var repository: DestinationRepository

    @Autowired
    lateinit var countryRepository: CountryRepository

    @Autowired
    lateinit var destinationTypeRepository: DestinationTypeRepository

    @Autowired
    lateinit var destinationAttractionRepository: DestinationAttractionRepository

    @Autowired
    lateinit var destinationRatingRepository: DestinationRatingRepository

    @Autowired
    lateinit var userFavoriteRepository: UserFavoriteRepository

    @GetMapping("destinations")
    fun getAll(): List<Destination> {
        return repository.findAll()
    }

    @GetMapping("destination/{id}")
    fun getDestination(@PathVariable("id") id: String): Any {
        return try {
            repository.findById(id).orElseThrow {
                RegisterNotFoundException("Destino não encontrado.")
            }
        } catch (exception: Exception) {
            exception
        }
    }

    @PostMapping("destination")
    fun createDestination(@RequestBody destinationInputDTO: DestinationInputDTO): Any {
        return try {
            val destination = createDestinationFromDTO(destinationInputDTO)
            repository.save(destination)
        } catch (exception: Exception) {
            exception
        }

    }

    @PutMapping("destination/{id}")
    fun updateDestination(
        @PathVariable("id") id: String,
        @RequestBody destinationInputDTO: DestinationInputDTO
    ): Any {
        return try {
            val destination = repository.findById(id).orElseThrow {
                RegisterNotFoundException("Destino não encontrado.")
            }
            repository.save(
                createDestinationFromDTO(
                    destinationInputDTO,
                    destination.id
                )
            )
        } catch (exception: Exception) {
            exception
        }

    }

    @DeleteMapping("/destination/{id}")
    fun deleteDestination(@PathVariable("id") id: String): Any {
        return try {
            val destination = repository.findById(id).orElseThrow {
                RegisterNotFoundException("Destino não encontrado.")
            }

            val attractionsByDestination = destinationAttractionRepository.findByDestinationId(destination.id)

            if (attractionsByDestination.isNotEmpty()) {
                throw BadRequestException("Destino não pode ser excluido devido a ter atrações associadas.")
            }

            val ratingsByDestination = destinationRatingRepository.findByDestinationId(destination.id)

            if (ratingsByDestination.isNotEmpty()) {
                throw BadRequestException("Destino não pode ser excluido devido a ter avaliações associadas.")
            }

            val favoritesByDestination = userFavoriteRepository.findByDestinationId(destination.id)

            if (favoritesByDestination.isNotEmpty()) {
                throw BadRequestException("Destino não pode ser excluido devido a ter favoritos associados.")
            }

            repository.delete(destination)
        } catch (exception: Exception) {
            exception
        }
    }

    @Throws
    private fun createDestinationFromDTO(
        destinationInputDTO: DestinationInputDTO,
        destinationId: String = ""
    ): Destination {
        val country = countryRepository.findById(destinationInputDTO.countryIso).orElseThrow {
            throw BadRequestException("Código ISO do país inválido.")
        }
        val destinationType = destinationTypeRepository.findById(destinationInputDTO.typeId).orElseThrow {
            BadRequestException("Tipo do destino inválido.")
        }

        return Destination(
            id = destinationId,
            name = destinationInputDTO.name,
            description = destinationInputDTO.description,
            photoUrl = destinationInputDTO.photoUrl,
            country = country,
            type = destinationType,
            rate = destinationInputDTO.rate
        )
    }
}