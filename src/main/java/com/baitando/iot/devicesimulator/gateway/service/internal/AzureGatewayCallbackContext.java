package com.baitando.iot.devicesimulator.gateway.service.internal;

import com.microsoft.azure.sdk.iot.device.DeviceClient;

/**
 * The context which stores necessary information needed for handling the result of processing a message sent to the
 * Azure IoT Hub device.
 */
public class AzureGatewayCallbackContext {
    private final int messageCount;
    private final DeviceClient deviceClient;
    private int messagesProcessed = 0;

    /**
     *
     * @param messageCount The count of all messages sent to the IoT Hub device.
     * @param deviceClient The device client which holds the connection to the IoT Hub device.
     */
    public AzureGatewayCallbackContext(int messageCount, DeviceClient deviceClient) {
        this.messageCount = messageCount;
        this.deviceClient = deviceClient;
    }

    /**
     *
     * @return The count of all messages sent to the IoT Hub device.
     */
    public int getMessageCount() {
        return messageCount;
    }

    /**
     *
     * @return The device client which holds the connection to the IoT Hub device.
     */
    public DeviceClient getDeviceClient() {
        return deviceClient;
    }

    /**
     *
     * @return The number of messages already processed.
     */
    public int getMessagesProcessed() {
        return messagesProcessed;
    }

    /**
     * Increase the count of already processed messages by one.
     */
    public void increaseMessagesProcessed() {
        messagesProcessed++;
    }
}
