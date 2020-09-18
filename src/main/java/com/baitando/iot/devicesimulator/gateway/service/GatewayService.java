package com.baitando.iot.devicesimulator.gateway.service;

import java.util.List;

/**
 * A service which allows to interact with an IoT device.
 */
public interface GatewayService {

    /**
     * Send a batch of messages to the IoT device.
     *
     * @param messageBodies Plain message bodies to send to the IoT device.
     * @throws MessageSendingException An error occured while sending the message to the IoT device.
     */
    void sendMessages(List<String> messageBodies) throws MessageSendingException;
}
