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

    val counterWithCache = Counter.builder("withCache").register(registry)
    val counterWithoutCache = Counter.builder("withOutCache").register(registry)
    val timerCache = Timer.builder("timeWithCache").register(registry)
    val timerWithoutCache = Timer.builder("timeWithOutCache").register(registry)

    fun createWithCache(): Domain {
        return timerCache.recordCallable<Domain> {
            cache.get("counter")

            val domain = Domain()
            repository.save(domain)

            cache.incr("counter")
            counterWithCache.increment()
            domain
        }


    }


    fun createWithoutCache(): Domain {
        return timerWithoutCache.recordCallable<Domain> {
            val domain = Domain()
            repository.save(domain)
            repository.countAllByValueLike("%a%")
            counterWithoutCache.increment()
            domain
        }

    }
}