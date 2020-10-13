package com.poc

import com.poc.model.Domain
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller constructor(private val service: Service){

    @PostMapping("/")
    fun create(): Domain {
        return service.createWithoutCache()
    }

    @PostMapping("/with-cache")
    fun createWithCache(): Domain {
        return service.createWithCache()
    }
}