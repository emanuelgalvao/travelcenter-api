package com.emanuelgalvao.travelcenter.repositories

import com.emanuelgalvao.travelcenter.entities.DestinationType
import org.springframework.data.jpa.repository.JpaRepository

interface DestinationTypeRepository: JpaRepository<DestinationType, String>