package com.emanuelgalvao.travelcenter.shared.repositories

import com.emanuelgalvao.travelcenter.shared.entities.Country
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository: JpaRepository<Country, String>