package com.poc

import com.poc.model.Domain
import com.poc.repository.Repo
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import org.springframework.stereotype.Service
import redis.clients.jedis.Jedis

@Service
class Service constructor(private val repository: Repo,
                          private val cache: Jedis,
                          private val registry: MeterRegistry) {

    val counterWithRelation = Counter.builder("withRelation").register(registry)
    val counterWithoutRelation = Counter.builder("withoutRelation").register(registry)
    val timerRelation = Timer.builder("timeWithRelation").register(registry)
    val timerWithoutRelation = Timer.builder("timeWithOutRelation").register(registry)

    fun createWithCacheRelation(): Domain {
        return timerRelation.recordCallable<Domain> {
            cache.get("counter")

            val domain = Domain()
            repository.save(domain)

            cache.incr("counter")
            counterWithRelation.increment()
            domain
        }


    }


    fun createWithoutRelation(): Domain {
        return timerWithoutRelation.recordCallable<Domain> {
            val domain = Domain()
            repository.save(domain)
            repository.countAllByValueLike("%a%")
            counterWithoutRelation.increment()
            domain
        }

    }
}