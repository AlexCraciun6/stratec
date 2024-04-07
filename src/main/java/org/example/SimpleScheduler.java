package org.example;

import org.example.models.*;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SimpleScheduler {

    public static SimpleSchedule generateSchedule(List<Machine> machines, List<Part> parts) {
        SimpleSchedule schedule = new SimpleSchedule();

        // Sort parts based on some criteria (e.g., the order they appear in the input file)
        // Here, you should implement your own sorting logic based on your requirements
        Collections.sort(parts);


        OperationSchedule dummyOperationSchedule = new OperationSchedule(new Operation(), LocalTime.MIN, LocalTime.MIN);
        PartSchedule prevPartSchedule = new PartSchedule(new Part("dummy", 1));
        prevPartSchedule.addOperationScheduleList(dummyOperationSchedule);

        for (Part part : parts) {
            for (int quantityIdx = 0; quantityIdx < part.getQuantity(); quantityIdx++) {
                PartSchedule newPartSchedule = schedulePart(part, machines, schedule);
                updateMachinesIntervals(newPartSchedule);
                updateTotalTime(newPartSchedule, schedule);
                schedule.addPartSchedules(newPartSchedule);
            }
        }
        return schedule;
    }

    private static void updateTotalTime(PartSchedule newPartSchedule, SimpleSchedule schedule) {
        int size = newPartSchedule.getOperationScheduleList().size();
        LocalTime end = newPartSchedule.getOperationScheduleList().get(size - 1).getEndTime();
        if(end.isAfter(schedule.getTotal()))
            schedule.setTotal(end);
    }

    private static void updateMachinesIntervals(PartSchedule newPartSchedule) {
        for(OperationSchedule operationSchedule: newPartSchedule.getOperationScheduleList()){
            Machine machine = operationSchedule.getOperation().getMachine();
            LocalTime start = operationSchedule.getStartTime(), finish = operationSchedule.getEndTime();
            machine.addBusyInterval(new Interval(start, finish));
        }
    }

    private static PartSchedule schedulePart(Part part, List<Machine> machines, SimpleSchedule schedule) {
        if(schedule.getPartSchedules().isEmpty())
            return scheduleFirstPart(part);

        PartSchedule partSchedule = new PartSchedule(part);

        for(Operation operation1: part.getOperations()){
            LocalTime startTime = getStartTimeByMachine(operation1.getMachine(), part);

            for (Operation operation : part.getOperations()) {
                LocalTime finishTime = startTime.plusSeconds(operation.getProcessingTime());
                partSchedule.addOperationScheduleList(new OperationSchedule(operation, startTime, finishTime));
                startTime = finishTime;
            }

            if(!schedulesConflict(machines, partSchedule))
                return partSchedule;

            partSchedule = new PartSchedule(part); // daca avem conflict, incepem un schedule nou
        }
        /// daca am ajuns aici -> piesa incepe la finalul procesarii celorlalte piese
        LocalTime startTime = schedule.getTotal();

        for (Operation operation : part.getOperations()) {
            LocalTime finishTime = startTime.plusSeconds(operation.getProcessingTime());
            partSchedule.addOperationScheduleList(new OperationSchedule(operation, startTime, finishTime));
            startTime = finishTime;
        }


        return partSchedule;
    }

    private static boolean schedulesConflict(List<Machine> machines, PartSchedule partSchedule){

        for(OperationSchedule operationSchedule: partSchedule.getOperationScheduleList()){
            LocalTime start = operationSchedule.getStartTime(), finish = operationSchedule.getEndTime();
            for(Machine machine: machines){
                if(machine.getName().equals(operationSchedule.getOperation().getMachine().getName()))
                    if(!machine.isAvailable(start, finish))
                        return true;
            }
        }

        return false;
    }

    private static LocalTime getStartTimeByMachine(Machine machine, Part part){
        LocalTime res;

        if(machine.getBusyIntervals().isEmpty())
            return LocalTime.MIN;
        else{
            int nrOfIntervals = machine.getBusyIntervals().size();
            Interval lastInterval = machine.getBusyIntervals().get(nrOfIntervals - 1);
            res = lastInterval.getEnd();
            if(machine.getCooldownTime() != 0)
                res = res.plusSeconds(machine.getCooldownTime());
        }

        return res;

        // am vrut sa reduc din timp scazand din rezulat timpul de procesare al operatiilor pana ajung la masinaria curenta
//        int i = 0, subtractValue = 0;
//        List<Operation> operations = part.getOperations();
//        while (! operations.get(i).getMachine().getName().equals(machine.getName())){
//            subtractValue += operations.get(i).getProcessingTime();
//            i += 1;
//        }
//        System.out.println(part + " " + res + " " + res.minusSeconds(subtractValue));
//
//        return res.minusSeconds(subtractValue);
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
