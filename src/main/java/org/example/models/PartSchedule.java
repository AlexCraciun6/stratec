package org.example.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PartSchedule {
    private Part part;
    private List<OperationSchedule> operationSchedules;

    public PartSchedule(Part part) {
        this.part = part;
        this.operationSchedules = new ArrayList<>();
    }

    // Method to add an operation schedule to the PartSchedule
    public void addOperationSchedule(OperationSchedule operationSchedule) {
        operationSchedules.add(operationSchedule);
    }

    public LocalTime getTotalProcessingTime() {
        LocalTime time = LocalTime.of(0, 0, 0);
        return time;
    }

    // Getters for part and operationSchedules

    // Other methods as needed...
}
