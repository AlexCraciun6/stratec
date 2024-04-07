package org.example.models;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SimpleSchedule {
    private List<PartSchedule> partSchedules;
    private LocalTime total;

    public SimpleSchedule() {
        this.partSchedules = new ArrayList<>();
        this.total = LocalTime.MIN;
    }

    public void addPartSchedules(PartSchedule partSchedule) {
        partSchedules.add(partSchedule);
    }

    public List<PartSchedule> getPartSchedules() {
        return partSchedules;
    }

    public void setPartSchedules(List<PartSchedule> partSchedules) {
        this.partSchedules = partSchedules;
    }

    public LocalTime getTotal() {
        return total;
    }

    public void setTotal(LocalTime total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SimpleSchedule{" +
                "partSchedules=" + partSchedules +
                '}';
    }
}
