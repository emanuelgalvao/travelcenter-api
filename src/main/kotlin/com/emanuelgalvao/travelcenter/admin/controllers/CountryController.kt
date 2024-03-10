package com.emanuelgalvao.travelcenter.admin.controllers

import com.emanuelgalvao.travelcenter.shared.entities.Country
import com.emanuelgalvao.travelcenter.shared.repositories.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/countries")
class CountryController {

    @Autowired
    lateinit var repository: CountryRepository

    @GetMapping
    fun getAll(): List<Country> {
        return repository.findAll()
    }
}