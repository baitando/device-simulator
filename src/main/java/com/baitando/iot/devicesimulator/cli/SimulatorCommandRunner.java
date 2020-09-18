package com.baitando.iot.devicesimulator.cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

/**
 * A runner which configures the {@link SimulatorCommand} command line command.
 */
@Component
public class SimulatorCommandRunner implements CommandLineRunner, ExitCodeGenerator {

    private final SimulatorCommand simulatorCommand;
    private final IFactory factory;

    private int exitCode;

    public SimulatorCommandRunner(SimulatorCommand simulatorCommand, IFactory factory) {
        this.simulatorCommand = simulatorCommand;
        this.factory = factory;
    }

    @Override
    public void run(String... args) {
        exitCode = new CommandLine(simulatorCommand, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
