package com.example.redismessagingsubscriber.subscriber;

import com.example.redismessagingsubscriber.services.ClientPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisSubscriber implements MessageListener {

    @Autowired
    private ClientPrinter clientPrinter;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        clientPrinter.jsonPrint(message.getBody());
    }
}
