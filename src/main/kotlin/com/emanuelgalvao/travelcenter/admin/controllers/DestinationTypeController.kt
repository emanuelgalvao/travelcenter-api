package com.emanuelgalvao.travelcenter.admin.controllers

import com.emanuelgalvao.travelcenter.shared.entities.DestinationType
import com.emanuelgalvao.travelcenter.shared.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.shared.exceptions.RegisterNotFoundException
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationRepository
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DestinationTypeController {

    @Autowired
    lateinit var repository: DestinationTypeRepository

    @Autowired
    lateinit var destinationRepository: DestinationRepository

    @GetMapping("/destinationTypes")
    fun getAllDestinationTypes(): List<DestinationType> {
        return repository.findAll()
    }

    @GetMapping("/destinationType/{id}")
    fun getDestinationType(@PathVariable("id") id: String): Any {
        return try {
            repository.findById(id).orElseThrow {
                RegisterNotFoundException("Tipo de destino não encontrado.")
            }
        } catch (exception: Exception) {
            exception
        }
    }

    @PostMapping("/destinationType")
    fun createDestinationType(@RequestBody destinationType: DestinationType): Any {
        return try {
            if (destinationType.name.isEmpty()) {
                throw BadRequestException("Nome do tipo de destino inválido.")
            } else if (destinationType.iconUrl.isEmpty()) {
                throw BadRequestException("URL de icone do tipo de destino inválido.")
            }
            repository.save(destinationType)
        } catch (exception: Exception) {
            exception
        }
    }

    @PutMapping("/destinationType/{id}")
    fun updateDestinationType(
        @PathVariable("id") id: String,
        @RequestBody newDestinationType: DestinationType
    ): Any {
        return try {
            if (newDestinationType.name.isEmpty()) {
                throw BadRequestException("Nome do tipo de destino inválido.")
            } else if (newDestinationType.iconUrl.isEmpty()) {
                throw BadRequestException("URL de icone do tipo de destino inválido.")
            } else {
                val destinationType = repository.findById(id).orElseThrow {
                    RegisterNotFoundException("Tipo de destino não encontrado.")
                }
                destinationType.apply {
                    name = newDestinationType.name
                    iconUrl = newDestinationType.iconUrl
                }
                repository.save(destinationType)
            }
        } catch (exception: Exception) {
            exception
        }
    }

    @DeleteMapping("/destinationType/{id}")
    fun deleteDestinationType(@PathVariable("id") id: String): Any {
        return try {
            val destinationType = repository.findById(id).orElseThrow {
                RegisterNotFoundException("Tipo de destino não encontrado.")
            }
            val destinationByType = destinationRepository.findByTypeId(destinationType.id)
            if (destinationByType.isNotEmpty()) {
                throw BadRequestException("Tipo de destino não pode ser excluido devido a ter destinos associados.")
            }
            repository.delete(destinationType)
        } catch (exception: Exception) {
            exception
        }
    }
}