package com.baitando.iot.devicesimulator.gateway.service;

/**
 * Indicates that there was an issue while sending messages.
 */
public class MessageSendingException extends RuntimeException {

    /**
     * Create an instance of the exception with the given parameters.
     *
     */
    public MessageSendingException() {
        super("Error sending messages");
    }
}
