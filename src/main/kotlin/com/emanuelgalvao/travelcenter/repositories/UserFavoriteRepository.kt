package com.emanuelgalvao.travelcenter.repositories

import com.emanuelgalvao.travelcenter.entities.UserFavorite
import org.springframework.data.jpa.repository.JpaRepository

interface UserFavoriteRepository: JpaRepository<UserFavorite, String> {

    fun findByUserId(id: String): List<UserFavorite>

    fun findByDestinationId(id: String): List<UserFavorite>
}