package com.emanuelgalvao.travelcenter.shared.repositories

import com.emanuelgalvao.travelcenter.shared.entities.UserFavorite
import org.springframework.data.jpa.repository.JpaRepository

interface UserFavoriteRepository: JpaRepository<UserFavorite, String> {

    fun findByUserId(id: String): List<UserFavorite>

    fun findByDestinationId(id: String): List<UserFavorite>
}