# Device Simulator
This project provides an IoT device simulator.
Currently it supports Azure IoT Hub devices only.

The device IoT device simulator uses a template based approach to generate the messages to send.

* The template definition file is any text file. It contains static test combined with dynamic placeholders in the format `${placeholder-name}`.
* The data file is a file which defines the data set. Currently, the project only supports the CSV format.

```csv
<placeholder-1>,<placeholder-2>
value-1-1,value-1-2
value-2-1,value-2-2
```

The data set provided in the data file is used to generate a message per entry using the template file.

As an example, imagine the following template file:

```json
{
    "deviceId": "${device-id}",
    "temperature": "${temperature}"
}
```

Now we provide the following data file:

```csv
device-id,temperature
test001,34
```

This leads to the following result, which will be sent to the cloud:

```json
{
    "deviceId": "test001",
    "temperature": "34"
}
```

## Run From IDE
In order to run the project from the IDE

1. Clone the project.
2. Import it as Maven project to your favorite IDE.
3. Create a launch configuration for `com.baitando.iot.devicesimulator.DeviceSimulatorApplication`.
4. Set the arguments in the launch configuration. We need to provide the following arguments: `-t=<path-to-template-file> -d=<path-to-data-file>`. Please replace the file path placeholders with the paths to your files.
5. Run the launch configuration.