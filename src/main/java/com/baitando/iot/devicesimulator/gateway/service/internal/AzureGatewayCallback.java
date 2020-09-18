package com.baitando.iot.devicesimulator.gateway.service.internal;

import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * A callback which is notified about the status and data returned by the Azure IoT Hub device as a result of processing
 * one or many messages sent to the IoT Hub device.
 * <br/>
 * This specific callback knows how many messages were sent. Once feedback for all messages is received, the callback
 * takes care of closing the IoT Hub device connection.
 */
public class AzureGatewayCallback implements IotHubEventCallback {

    private final Logger logger = LoggerFactory.getLogger(AzureGatewayCallback.class);

    @Override
    public void execute(IotHubStatusCode responseStatus, Object callbackContext) {
        AzureGatewayCallbackContext azureGatewayCallbackContext = (AzureGatewayCallbackContext) callbackContext;
        azureGatewayCallbackContext.increaseMessagesProcessed();

        logger.debug("Message processed [messageCount={}, messagesProcessed={}, responseStatus={}]",
                azureGatewayCallbackContext.getMessageCount(), azureGatewayCallbackContext.getMessagesProcessed(),
                responseStatus.name());

        if (azureGatewayCallbackContext.getMessagesProcessed() == azureGatewayCallbackContext.getMessageCount()) {
            logger.debug("All messages processed so we will close the device client connection");

            try {
                azureGatewayCallbackContext.getDeviceClient().closeNow();
            } catch (IOException e) {
                logger.error("Error closing device client connection", e);
            }
        }
    }
}
