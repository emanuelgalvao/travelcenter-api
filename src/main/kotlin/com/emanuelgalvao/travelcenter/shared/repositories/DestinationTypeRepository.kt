package com.emanuelgalvao.travelcenter.shared.repositories

import com.emanuelgalvao.travelcenter.shared.entities.DestinationType
import org.springframework.data.jpa.repository.JpaRepository

interface DestinationTypeRepository: JpaRepository<DestinationType, String>