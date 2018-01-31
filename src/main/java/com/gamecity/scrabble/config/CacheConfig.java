package com.gamecity.scrabble.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport
{
    @Bean
    public RedisCacheManager cacheManager(JedisConnectionFactory connectionFactory)
    {
        return RedisCacheManager.create(connectionFactory);
    }
}
