package com.baitando.iot.devicesimulator.gateway.service.internal;

import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.MessageType;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Converter which takes care of converting raw input to messages, which can be sent to the Azure IoT Hub device.
 */
public final class AzureMessageConverter {

    private AzureMessageConverter() {
        // Prevent instantiation
    }

    /**
     * Convert raw input to a message, which can be sent to the IoT Hub device.
     *
     * @param messageBody The message body to send.
     * @return The message representation needed for interaction with the IoT Hub device.
     */
    static Message convertToMessage(String messageBody) {
        Message message = new Message(messageBody);

        message.setMessageType(MessageType.DEVICE_TELEMETRY);
        message.setMessageId(UUID.randomUUID().toString());

        return message;
    }

    /**
     * Convert a list of raw input to a list of messages, which can be sent to the IoT Hub device.
     *
     * @param messageBodies The message bodies to send.
     * @return The message representations needed for interaction with the IoT Hub device.
     */
    static List<Message> convertToMessages(List<String> messageBodies) {
        return messageBodies
                .stream()
                .map(AzureMessageConverter::convertToMessage)
                .collect(Collectors.toList());
    }
}
