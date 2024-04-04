package org.example.models;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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


    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "OperationSchedule{" +
                "machineName=" + operation.getMachine().getName() +
                ", startTime=" + startTime.format(formatter) +
                ", endTime=" + endTime.format(formatter) +
                '}';
    }
}
