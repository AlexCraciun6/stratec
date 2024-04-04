package org.example;

import org.example.models.*;

import java.time.LocalTime;
import java.util.List;

public class SimpleScheduler {

    public static SimpleSchedule generateSchedule(List<Machine> machines, List<Part> parts, List<Operation> operations) {
        SimpleSchedule schedule = new SimpleSchedule();

        // Sort parts based on some criteria (e.g., the order they appear in the input file)
        // Here, you should implement your own sorting logic based on your requirements


        OperationSchedule dummyOperationSchedule = new OperationSchedule(new Operation(), LocalTime.MIN, LocalTime.MIN);
        PartSchedule prevPartSchedule = new PartSchedule(new Part("dummy", 1));
        prevPartSchedule.addOperationScheduleList(dummyOperationSchedule);

        for (Part part : parts) {
            for (int quantityIdx = 0; quantityIdx < part.getQuantity(); quantityIdx++) {
                PartSchedule newPartSchedule = schedulePart(part, machines, operations, schedule);
                schedule.addPartSchedules(newPartSchedule);
            }
        }
        return schedule;
    }

    private static PartSchedule schedulePart(Part part, List<Machine> machines, List<Operation> operations, SimpleSchedule schedule) {
        if(schedule.getPartSchedules().isEmpty())
            return scheduleFirstPart(part);

        PartSchedule partSchedule = new PartSchedule(part);
//        PartSchedule prevPartSchedule = schedule.getPartSchedules().get(schedule.getPartSchedules().size() - 1);
//        int lastOperationIdx = prevPartSchedule.getOperationScheduleList().size() - 1;
//        LocalTime startTime = prevPartSchedule.getOperationScheduleList().get(lastOperationIdx).getEndTime();


        for(Operation operation1: part.getOperations()){
            LocalTime startTime = getStartTimeByMachine(operation1.getMachine());

            for (Operation operation : part.getOperations()) {
                LocalTime finishTime = startTime.plusSeconds(operation.getProcessingTime());
                partSchedule.addOperationScheduleList(new OperationSchedule(operation, startTime, finishTime));
                startTime = finishTime;
            }

            if(!schedulesConflict())
                return partSchedule;
        }





        return partSchedule;
    }

    private static boolean schedulesConflict(){
        return false;
    }

    private static LocalTime getStartTimeByMachine(Machine machine){
        LocalTime localTime = null;

        return localTime;
    }

    private static PartSchedule scheduleFirstPart(Part part){
        PartSchedule partSchedule = new PartSchedule(part);
        LocalTime startTime = LocalTime.MIN;

        for (Operation operation : part.getOperations()) {
            LocalTime finishTime = startTime.plusSeconds(operation.getProcessingTime());
            partSchedule.addOperationScheduleList(new OperationSchedule(operation, startTime, finishTime));
            startTime = finishTime;
        }

        return partSchedule;
    }
}
