package org.example.models;
import java.time.LocalTime;

public class OperationSchedule {
    private Operation operation;
    private LocalTime startTime;
    private LocalTime endTime;

    public OperationSchedule(Operation operation, LocalTime startTime, LocalTime endTime) {
        this.operation = operation;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters for operation, startTime, and endTime

    // Other methods as needed...
}
