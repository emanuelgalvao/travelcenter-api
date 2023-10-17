package com.emanuelgalvao.travelcenter.repositories

import com.emanuelgalvao.travelcenter.entities.Country
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository: JpaRepository<Country, String>