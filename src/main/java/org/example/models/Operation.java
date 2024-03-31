package org.example.models;

public class Operation {
    private Machine machine;
    private int processingTime;

    public Operation(Machine machine, int processingTime) {
        this.machine = machine;
        this.processingTime = processingTime;
    }

    // Getters and setters
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "machine=" + machine +
                ", processingTime=" + processingTime +
                '}';
    }
}
