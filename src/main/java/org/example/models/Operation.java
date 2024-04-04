package org.example.models;

public class Operation {
    private Machine machine;
    private int processingTime;
    private int partId;

    public Operation(Machine machine, int processingTime, int partId) {
        this.machine = machine;
        this.processingTime = processingTime;
        this.partId = partId;
    }
    public Operation(){}

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

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "machine=" + machine +
                ", processingTime=" + processingTime +
                ", partId=" + partId +
                '}';
    }
}
