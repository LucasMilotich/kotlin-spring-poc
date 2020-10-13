package com.poc.repository

import com.poc.model.Domain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Repo : CrudRepository<Domain, Long>{
    fun countAllByValueLike(value : String): Long
    //fun countAllByValueContains(value: String)
}