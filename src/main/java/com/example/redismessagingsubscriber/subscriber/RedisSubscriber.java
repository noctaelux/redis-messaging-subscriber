package com.example.redismessagingsubscriber.subscriber;

import com.example.redismessagingsubscriber.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RedisSubscriber implements MessageListener {

    public static List<String> messageList = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        ObjectMapper mapper = new ObjectMapper();
        try {
            Cliente cliente = mapper.readValue(message.getBody(), Cliente.class);
            System.out.println(cliente.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Message received: "+new String(message.getBody()));
    }
}
