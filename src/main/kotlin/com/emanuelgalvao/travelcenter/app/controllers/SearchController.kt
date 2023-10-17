package com.emanuelgalvao.travelcenter.app.controllers

import com.emanuelgalvao.travelcenter.app.dto.SearchDestinationDTO
import com.emanuelgalvao.travelcenter.entities.Destination
import com.emanuelgalvao.travelcenter.repositories.DestinationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/app")
class SearchController {

    @Autowired
    lateinit var repository: DestinationRepository

    @GetMapping("/search")
    fun search(@RequestParam term: String): List<SearchDestinationDTO> {
        return repository.findByNameContains(term).map {
            SearchDestinationDTO(
                id = it.id,
                name = it.name
            )
        }
    }
}