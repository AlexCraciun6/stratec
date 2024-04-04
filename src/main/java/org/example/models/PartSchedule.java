package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class PartSchedule {
    private List<OperationSchedule> operationScheduleList;
    private final Part part;

    public PartSchedule(Part part) {
        this.operationScheduleList = new ArrayList<>();
        this.part = part;
    }
    public void addOperationScheduleList(OperationSchedule operationSchedule) {
        operationScheduleList.add(operationSchedule);
    }

    public List<OperationSchedule> getOperationScheduleList() {
        return operationScheduleList;
    }

    public void setOperationScheduleList(List<OperationSchedule> operationScheduleList) {
        this.operationScheduleList = operationScheduleList;
    }

    public Part getPart() {
        return part;
    }

    @Override
    public String toString() {
        return "PartSchedule{" +
                "operationScheduleList=" + operationScheduleList +
                ", part=" + part +
                '}';
    }
}
