package com.baitando.iot.devicesimulator.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of the gateway.
 */
@Configuration
@ConfigurationProperties(prefix = "simulator.gateway")
public class GatewayConfig {

    private String connection;

    /**
     *
     * @return Connection string used to connect to the IoT Hub device.
     */
    public String getConnection() {
        return connection;
    }

    /**
     *
     * @param connection Connection string used to connect to the IoT Hub device.
     */
    public void setConnection(String connection) {
        this.connection = connection;
    }
}
