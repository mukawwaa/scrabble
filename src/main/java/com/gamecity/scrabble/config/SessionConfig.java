package com.gamecity.scrabble.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import com.gamecity.scrabble.Constants;
import com.gamecity.scrabble.controller.ChatController;
import com.gamecity.scrabble.controller.ContentController;

@Configuration
@EnableRedisHttpSession
public class SessionConfig extends AbstractHttpSessionApplicationInitializer
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
    public JedisConnectionFactory connectionFactory()
    {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        return new JedisConnectionFactory(redisConfiguration);
    }

    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory connectionFactory)
    {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(JedisConnectionFactory connectionFactory)
    {
        RedisMessageListenerContainer messageListenerContainer = new RedisMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.addMessageListener(chatController, new PatternTopic(Constants.RedisListener.BOARD_CHAT));
        messageListenerContainer.addMessageListener(contentController, new PatternTopic(Constants.RedisListener.BOARD_CONTENT));
        return messageListenerContainer;
    }
}
