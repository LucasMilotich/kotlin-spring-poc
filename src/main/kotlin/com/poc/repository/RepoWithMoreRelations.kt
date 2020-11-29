package com.poc.repository

import com.poc.model.DomainWithMoreRelations
import com.poc.model.DomainWithRelation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RepoWithMoreRelations : CrudRepository<DomainWithMoreRelations, Long>{
    fun countAllByValueLike(value : String): Long

    //fun countAllByValueContains(value: String)
}