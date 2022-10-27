package com.example.redismessagingsubscriber.config;

import com.example.redismessagingsubscriber.subscriber.RedisSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Autowired
    RedisConnectionFactory connectionFactory;

    @Bean
    MessageListenerAdapter messageListener(){
        return new MessageListenerAdapter(new RedisSubscriber());
    }

    @Bean
    RedisMessageListenerContainer redisContainer(MessageListenerAdapter messageListener, ChannelTopic topic){
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener, topic);
        return container;
    }

    @Bean
    ChannelTopic topic(){
        return new ChannelTopic("stream:cliente");
    }

}
