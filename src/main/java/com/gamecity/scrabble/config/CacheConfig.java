package com.gamecity.scrabble.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import com.gamecity.scrabble.Constants;
import com.gamecity.scrabble.controller.ChatController;
import com.gamecity.scrabble.controller.ContentController;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport
{
    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private Integer redisPort;

    @Autowired
    private ChatController chatController;

    @Autowired
    private ContentController contentController;

    @Bean
    public JedisConnectionFactory redisConnectionFactory()
    {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        return new JedisConnectionFactory(redisConfiguration);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory factory)
    {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager cacheManager(JedisConnectionFactory connectionFactory)
    {
        return RedisCacheManager.create(connectionFactory);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory)
    {
        RedisMessageListenerContainer messageListenerContainer = new RedisMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(factory);
        messageListenerContainer.addMessageListener(chatController, new PatternTopic(Constants.RedisListener.BOARD_CHAT));
        messageListenerContainer.addMessageListener(contentController, new PatternTopic(Constants.RedisListener.BOARD_CONTENT));
        return messageListenerContainer;
    }
}
