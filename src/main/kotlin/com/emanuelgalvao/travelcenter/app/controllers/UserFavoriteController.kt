package com.emanuelgalvao.travelcenter.app.controllers

import com.emanuelgalvao.travelcenter.app.dto.request.AddUserFavoriteRequestDTO
import com.emanuelgalvao.travelcenter.app.dto.request.GetUserFavoriteRequestDTO
import com.emanuelgalvao.travelcenter.entities.UserFavorite
import com.emanuelgalvao.travelcenter.exceptions.BadRequestException
import com.emanuelgalvao.travelcenter.exceptions.RegisterNotFoundException
import com.emanuelgalvao.travelcenter.repositories.DestinationRepository
import com.emanuelgalvao.travelcenter.repositories.UserFavoriteRepository
import com.emanuelgalvao.travelcenter.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
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

    @PostMapping("/favorites")
    fun getUserFavorites(@RequestBody getUserFavoriteRequestDTO: GetUserFavoriteRequestDTO): List<UserFavorite> {
        return userFavoriteRepository.findByUserId(getUserFavoriteRequestDTO.userId)
    }

    @PostMapping("/addFavorite")
    fun addFavorite(@RequestBody addUserFavoriteRequestDTO: AddUserFavoriteRequestDTO): UserFavorite {
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
        return userFavoriteRepository.save(userFavorite)
    }

    @DeleteMapping("/removeFavorite/{id}")
    fun removeFavorite(@PathVariable("id") id: String) {
        val favorite = userFavoriteRepository.findById(id).orElseThrow {
            RegisterNotFoundException("Favorito não encontrado.")
        }
        return userFavoriteRepository.delete(favorite)
    }
}