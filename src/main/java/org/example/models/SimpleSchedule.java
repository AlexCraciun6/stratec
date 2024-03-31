package org.example.models;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SimpleSchedule {
    private List<PartSchedule> partSchedules;
    private LocalTime totalProcessingTime;

    public SimpleSchedule() {
        this.partSchedules = new ArrayList<>();
        this.totalProcessingTime = LocalTime.of(0, 0, 0);
    }

    // Method to add a part's schedule to the SimpleSchedule
    public void addPartSchedule(PartSchedule partSchedule) {
        partSchedules.add(partSchedule);
        totalProcessingTime = totalProcessingTime.plusSeconds(partSchedule.getTotalProcessingTime().toSecondOfDay());
    }

    // Getters for partSchedules and totalProcessingTime

    // Other methods as needed...
}
