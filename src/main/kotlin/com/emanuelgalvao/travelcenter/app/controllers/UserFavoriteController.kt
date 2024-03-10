package com.emanuelgalvao.travelcenter.app.controllers

import com.emanuelgalvao.travelcenter.app.dto.request.AddUserFavoriteRequestDTO
import com.emanuelgalvao.travelcenter.app.dto.response.UserFavoriteResponseDTO
import com.emanuelgalvao.travelcenter.app.utils.toResponseDTO
import com.emanuelgalvao.travelcenter.shared.entities.UserFavorite
import com.emanuelgalvao.travelcenter.shared.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.shared.exceptions.RegisterNotFoundException
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationRepository
import com.emanuelgalvao.travelcenter.shared.repositories.UserFavoriteRepository
import com.emanuelgalvao.travelcenter.shared.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/app")
class UserFavoriteController {

    @Autowired
    lateinit var userFavoriteRepository: UserFavoriteRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var destinationRepository: DestinationRepository

    @GetMapping("/favorites/{id}")
    fun getUserFavorites(@PathVariable("id") userId: String): List<UserFavoriteResponseDTO> {
        return userFavoriteRepository.findByUserId(userId).map {
            it.toResponseDTO()
        }
    }

    @PostMapping("/addFavorite")
    fun addFavorite(@RequestBody addUserFavoriteRequestDTO: AddUserFavoriteRequestDTO): UserFavoriteResponseDTO {
        val user = userRepository.findById(addUserFavoriteRequestDTO.userId).orElseThrow {
            BadRequestException("Usuário inválido.")
        }
        val destination = destinationRepository.findById(addUserFavoriteRequestDTO.destinationId).orElseThrow {
            BadRequestException("Destino inválido.")
        }
        val userFavorite = UserFavorite(
            user = user,
            destination = destination
        )
        return userFavoriteRepository.save(userFavorite).toResponseDTO()
    }

    @DeleteMapping("/removeFavorite/{id}")
    fun removeFavorite(@PathVariable("id") id: String) {
        val favorite = userFavoriteRepository.findById(id).orElseThrow {
            RegisterNotFoundException("Favorito não encontrado.")
        }
        return userFavoriteRepository.delete(favorite)
    }
}