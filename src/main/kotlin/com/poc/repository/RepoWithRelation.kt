package com.poc.repository

import com.poc.model.DomainWithRelation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Repo : CrudRepository<DomainWithRelation, Long>{
    fun countAllByValueLike(value : String): Long
    //fun countAllByValueContains(value: String)
}