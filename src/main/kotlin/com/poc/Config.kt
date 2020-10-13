package com.poc

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import redis.clients.jedis.Jedis


@Configuration
class Config {

    @Bean
    fun createCache(): Jedis {
        val r = Jedis("127.0.0.1", 6379)
        return r
    }

    @Bean
    fun timedAspect(registry: MeterRegistry?): TimedAspect? {
        return TimedAspect(registry!!)
    }
}