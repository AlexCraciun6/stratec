package org.example.models;
import java.util.ArrayList;
import java.util.List;

public class SimpleSchedule {
    private List<PartSchedule> partSchedules;

    public SimpleSchedule() {
        this.partSchedules = new ArrayList<>();
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


    @Override
    public String toString() {
        return "SimpleSchedule{" +
                "partSchedules=" + partSchedules +
                '}';
    }
}
