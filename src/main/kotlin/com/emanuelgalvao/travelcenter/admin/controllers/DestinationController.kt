package com.emanuelgalvao.travelcenter.admin.controllers

import com.emanuelgalvao.travelcenter.admin.dto.request.DestinationRequestDTO
import com.emanuelgalvao.travelcenter.shared.entities.Destination
import com.emanuelgalvao.travelcenter.shared.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.shared.exceptions.RegisterNotFoundException
import com.emanuelgalvao.travelcenter.shared.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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
    fun createDestination(@RequestBody destinationRequestDTO: DestinationRequestDTO): Any {
        return try {
            val destination = createDestinationFromDTO(destinationRequestDTO)
            repository.save(destination)
        } catch (exception: Exception) {
            exception
        }

    }

    @PutMapping("destination/{id}")
    fun updateDestination(
        @PathVariable("id") id: String,
        @RequestBody destinationRequestDTO: DestinationRequestDTO
    ): Any {
        return try {
            val destination = repository.findById(id).orElseThrow {
                RegisterNotFoundException("Destino não encontrado.")
            }
            repository.save(
                createDestinationFromDTO(
                    destinationRequestDTO,
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
        destinationInputDTO: DestinationRequestDTO,
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