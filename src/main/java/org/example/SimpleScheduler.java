package org.example;

import org.example.models.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class SimpleScheduler {

    public static SimpleSchedule generateSchedule(List<Machine> machines, List<Part> parts, List<Operation> operations) {
        SimpleSchedule schedule = new SimpleSchedule();

        // Sort parts based on some criteria (e.g., the order they appear in the input file)
        // Here, you should implement your own sorting logic based on your requirements

        Part firstPart = parts.get(0);
        PartSchedule partSchedule = new PartSchedule(firstPart);
        LocalTime startTime = LocalTime.MIN;

        for(Operation operation: firstPart.getOperations())
        {
            LocalTime finishTime = startTime.plusSeconds(operation.getProcessingTime());
            partSchedule.addOperationScheduleList(new OperationSchedule(operation, startTime, finishTime));
            startTime = finishTime;
        }
        schedule.addPartSchedules(partSchedule);

//        schedulePartOperations(schedule, firstPart, machines, operations);

        for (int i = 1; i < parts.size(); i++) {
            Part part = parts.get(i);
            schedulePartOperations(schedule, part, machines, operations);
        }

        return schedule;
    }

    private static void schedulePartOperations(SimpleSchedule schedule, Part part, List<Machine> machines, List<Operation> operations) {

    }

}
