package org.example;

import org.example.models.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputParser parser = new InputParser();
        String filePath = "Input_One.txt";

        List<Machine> machines = parser.parseMachines(filePath);
        List<Part> parts = parser.parseParts(filePath);
        List<Operation> operations = parser.parsePartOperations(filePath);

        parser.printToConsole(machines, parts);

        System.out.println("Simple schedule:");
        SimpleSchedule schedule = SimpleScheduler.generateSchedule(machines, parts);
        for(PartSchedule partSchedule: schedule.getPartSchedules()){
            System.out.println(partSchedule.getPart());
            for(OperationSchedule operationSchedule: partSchedule.getOperationScheduleList())
                System.out.println(operationSchedule);
        }
        System.out.println("The total processing time: " + schedule.getTotal());
    }
}