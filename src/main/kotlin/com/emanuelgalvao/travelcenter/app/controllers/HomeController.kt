package com.emanuelgalvao.travelcenter.app.controllers

import com.emanuelgalvao.travelcenter.app.dto.HomeDataDTO
import com.emanuelgalvao.travelcenter.app.dto.HomeSectionDTO
import com.emanuelgalvao.travelcenter.app.dto.HomeSectionDestinationDTO
import com.emanuelgalvao.travelcenter.entities.Country
import com.emanuelgalvao.travelcenter.entities.Destination
import com.emanuelgalvao.travelcenter.repositories.DestinationRepository
import com.emanuelgalvao.travelcenter.repositories.DestinationTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/app")
class HomeController {

    @Autowired
    lateinit var destinationTypeRepository: DestinationTypeRepository

    @Autowired
    lateinit var destinationRepository: DestinationRepository

    @GetMapping("/home")
    fun getHomeData(): HomeDataDTO {
        val destinationTypes = destinationTypeRepository.findAll()
        val sections: MutableList<HomeSectionDTO> = mutableListOf()

        val mostRatedDestinations = destinationRepository.findTop5ByOrderByRate().map {
            mapDestinationToDTO(it)
        }

        val inBrazilDestinations = destinationRepository.findTop5ByCountry(Country("BR", "")).map {
            mapDestinationToDTO(it)
        }

        sections.add(
            HomeSectionDTO(
                name = "Destinos mais bem avaliados",
                destinations = mostRatedDestinations
            )
        )

        sections.add(
            HomeSectionDTO(
                name = "Destinos no Brasil",
                destinations = inBrazilDestinations
            )
        )

        return HomeDataDTO(
            destinationTypes = destinationTypes,
            sections = sections
        )
    }

    private fun mapDestinationToDTO(destination: Destination): HomeSectionDestinationDTO = destination.run {
        return  HomeSectionDestinationDTO(
            id = this.id,
            name = this.name,
            photo = this.photoUrl,
            rate = this.rate.toString()
        )
    }

}