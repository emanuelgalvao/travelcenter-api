package com.emanuelgalvao.travelcenter.app.controllers

import com.emanuelgalvao.travelcenter.app.dto.response.HomeDataResponseDTO
import com.emanuelgalvao.travelcenter.app.dto.response.HomeSectionResponseDTO
import com.emanuelgalvao.travelcenter.app.utils.TipUtils
import com.emanuelgalvao.travelcenter.app.utils.toResponseDTO
import com.emanuelgalvao.travelcenter.shared.entities.Country
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationRepository
import com.emanuelgalvao.travelcenter.shared.repositories.DestinationTypeRepository
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
    fun getHomeData(): HomeDataResponseDTO {
        val destinationTypes = destinationTypeRepository.findAll()
        val sections: MutableList<HomeSectionResponseDTO> = mutableListOf()

        val allDestinations = destinationRepository.findAll()

        val mostRatedDestinations = allDestinations.filter { it.country.iso != "BR" }.map {
            it.toResponseDTO()
        }

        val inBrazilDestinations = destinationRepository.findTop5ByCountry(Country("BR", "")).map {
            it.toResponseDTO()
        }

        sections.add(
            HomeSectionResponseDTO(
                name = "Destinos fora do Brasil",
                destinations = mostRatedDestinations
            )
        )

        sections.add(
            HomeSectionResponseDTO(
                name = "Destinos no Brasil",
                destinations = inBrazilDestinations
            )
        )

        return HomeDataResponseDTO(
            tip = TipUtils.getRandomTip(),
            destinationTypes = destinationTypes,
            sections = sections
        )
    }

}