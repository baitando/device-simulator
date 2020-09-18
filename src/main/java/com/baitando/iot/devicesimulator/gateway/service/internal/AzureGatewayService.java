package com.baitando.iot.devicesimulator.gateway.service.internal;

import com.baitando.iot.devicesimulator.gateway.config.GatewayConfig;
import com.baitando.iot.devicesimulator.gateway.service.GatewayService;
import com.baitando.iot.devicesimulator.gateway.service.MessageSendingException;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.baitando.iot.devicesimulator.gateway.service.internal.AzureMessageConverter.convertToMessages;

/**
 * Implementation of a {@link GatewayService}, which sends data to an Azure IoT Hub device.
 */
@Service
public class AzureGatewayService implements GatewayService {

    private final Logger logger = LoggerFactory.getLogger(AzureGatewayService.class);

    private final GatewayConfig config;

    public AzureGatewayService(GatewayConfig config) {
        this.config = config;
    }

    @Override
    public void sendMessages(List<String> messageBodies) {
        if (messageBodies == null) {
            throw new IllegalArgumentException("Message bodies must not be null");
        }
        logger.debug("Sending messages to IoT Hub [messageCount={}]", messageBodies.size());

        try {
            sendMessagesInternal(convertToMessages(messageBodies));
        } catch (URISyntaxException | IOException e) {
            throw new MessageSendingException();
        }
    }

    private void sendMessagesInternal(List<Message> messages) throws URISyntaxException, IOException {
        // Connection is opened for the batch. Once we received an IoT Hub response for each message, the
        // callback closes the connection.
        DeviceClient client = new DeviceClient(config.getConnection(), IotHubClientProtocol.MQTT);
        client.open();

        client.sendEventBatchAsync(
                messages,
                new AzureGatewayCallback(),
                new AzureGatewayCallbackContext(messages.size(), client));
    }
}
