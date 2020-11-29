package com.poc

import com.poc.model.DomainWithMoreRelations
import com.poc.model.DomainWithRelation
import com.poc.model.DomainWithoutRelation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller constructor(private val service: Service){

    @PostMapping("/")
    fun create(): DomainWithRelation {
        return service.createWithRelation()
    }

    @PostMapping("/with-more-relations")
    fun createWithMoreRelations(): DomainWithMoreRelations {
        return service.createWithMoreRelation()
    }

    @PostMapping("/with-out-relation")
    fun createWithCache(): DomainWithoutRelation {
        return service.createWithoutRelation()
    }
}