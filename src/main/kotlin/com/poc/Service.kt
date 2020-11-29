package com.poc

import com.poc.model.DomainWithMoreRelations
import com.poc.model.DomainWithRelation
import com.poc.model.DomainWithoutRelation
import com.poc.repository.Repo
import com.poc.repository.RepoWithMoreRelations
import com.poc.repository.RepoWithoutRelation
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import org.springframework.stereotype.Service
import redis.clients.jedis.Jedis
import javax.transaction.Transactional

@Service
class Service constructor(
    private val repository: Repo,
    private val repositoryWithoutRelation: RepoWithoutRelation,
    private val repoWithMoreRelations: RepoWithMoreRelations,
    private val cache: Jedis,
    private val registry: MeterRegistry
) {

    val counterWithRelation = Counter.builder("withRelation").register(registry)
    val counterWithoutRelation = Counter.builder("withoutRelation").register(registry)
    val timerRelation = Timer.builder("timeWithRelation").register(registry)
    val timerWithoutRelation = Timer.builder("timeWithOutRelation").register(registry)

    fun createWithRelation(): DomainWithRelation {
        return timerRelation.recordCallable<DomainWithRelation> {


            val data = _createWithRelation()
            repository.findById(data.id)
            data

        }
    }

    fun createWithMoreRelation(): DomainWithMoreRelations {
        return timerRelation.recordCallable<DomainWithMoreRelations> {


            val data = _createWithMoreRelation()
            repository.findById(data.id)
            data

        }
    }

    fun createWithoutRelation(): DomainWithoutRelation {
        return timerWithoutRelation.recordCallable<DomainWithoutRelation> {
          val data = _createWithoutRelation()
            repositoryWithoutRelation.findById(data.id)
            data
        }
    }
    @Transactional
    fun _createWithRelation(): DomainWithRelation {
        val domain = DomainWithRelation()
        return  repository.save(domain)
    }
    @Transactional
    fun _createWithoutRelation(): DomainWithoutRelation {
        val domain = DomainWithoutRelation()
        return repositoryWithoutRelation.save(domain)
    }

    @Transactional
    fun _createWithMoreRelation(): DomainWithMoreRelations {
        val domain = DomainWithMoreRelations()
        return repoWithMoreRelations.save(domain)
    }
}