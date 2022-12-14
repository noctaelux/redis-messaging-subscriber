package com.example.redismessagingsubscriber.services;

import com.example.redismessagingsubscriber.model.Cliente;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@RequiredArgsConstructor
public class ClientPrinterImpl implements ClientPrinter {

    Logger LOG = LoggerFactory.getLogger(ClientPrinterImpl.class);

    UnmarshallMessage<Cliente> unmarshallMessage;

    @Override
    public void jsonPrint(byte[] message) {
        try {
            Cliente cliente = unmarshallMessage.fromJson(new String(message),Cliente.class);
            LOG.debug(cliente.toString());
        } catch (JsonProcessingException e) {
            throw new InvalidParameterException("El mensaje no puede ser convertido a Cliente.");
        }
    }
}
