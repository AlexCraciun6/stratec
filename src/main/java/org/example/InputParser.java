package org.example;

import org.example.models.Machine;
import org.example.models.Operation;
import org.example.models.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    private List<Machine> machines = new ArrayList<>();
    private List<Part> parts = new ArrayList<>();

    public List<Machine> parseMachines(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean readingMachines = false, readingMachinesFeatures = false;
            String machineName = null;
            int machinesIdx = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) continue;

                if (line.startsWith("Available machines:")) {
                    readingMachines = true;
                    continue;
                }

                if (line.startsWith("Machine features:")) {
                    readingMachines = false;
                    readingMachinesFeatures = true;
                    continue;
                }

                if (readingMachines) {
                    // Split the line by '.', assuming format: <number>. <machine name>
                    String[] parts = line.split("\\.");
                    if (parts.length < 2) {
                        System.err.println("Invalid machine data format: " + line);
                        continue;
                    }
                    machineName = parts[1].trim();

                    // Create a machine and add it to the list
                    machines.add(new Machine(machineName, null, null));
                }
                if (readingMachinesFeatures) {
                    // Check if the line contains machine features
                    if (line.contains("- Capacity:")) {
                        String[] parts = line.split(":");
                        if (parts.length > 2) {
                            String capacity = parts[2].trim().split(" ")[0];
                            machines.get(machinesIdx).setCapacity(MachineCapacityMapper.getCapacity(capacity));
                        }

                    } else if (line.contains("- Cooldown time:")) {
                        String[] parts = line.split(":");
                        if (parts.length > 1) {
                            String cooldownTime = parts[1].trim().split(" ")[0];
                            Integer cooldownValue = 0;
                            if(!cooldownTime.equals("none"))
                                cooldownValue = Integer.parseInt(cooldownTime);
                            machines.get(machinesIdx).setCooldownTime(cooldownValue);
                            machinesIdx++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return machines;
    }

    public List<Part> parseParts(String filePath) {


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean readingPartList = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) continue;

                if (line.startsWith("Part list:")) {
                    readingPartList = true;
                    continue;
                }

                if (line.startsWith("Part operations:")) {
                    break; // Done reading part list
                }

                if (readingPartList) {
                    String[] partsInfo = line.split("\\.");
                    if (partsInfo.length < 2) {
                        System.err.println("Invalid part data format: " + line);
                        continue;
                    }

                    String partName = partsInfo[1].trim().split("-")[0].trim();
                    int quantity = Integer.parseInt(partsInfo[1].trim().split("-")[1].trim().split(" ")[0]); // Extract quantity as an integer

                    // Create a part and add it to the list
                    parts.add(new Part(partName, quantity));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parts;
    }

    public List<Operation> parsePartOperations(String filePath) {
        List<Operation> operations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean readingPartOperations = false;
            int partIdx = -1;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) continue;

                if (line.startsWith("Part operations:")) {
                    readingPartOperations = true;
                    continue;
                }

                if (readingPartOperations) {

                    if (line.matches("^\\d+:.*")) {
                        // This line represents a new part operation
                        partIdx += 1;
                    }
                    // This line contains operation details
                    String currentPath = line.split("-")[1];
                    String[] pathParts = currentPath.split(":");
                    String machineName = pathParts[0].trim();
                    int processingTime = Integer.parseInt(pathParts[1].trim().split(" ")[0]); // Extract processing time as an integer

                    Machine machine = findMachineByName(machineName, machines);
                    if (machine != null){
                        Operation operation = new Operation(machine, processingTime);
                        operations.add(operation);
                        parts.get(partIdx).addOperation(operation);
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return operations;
    }

    // Helper method to find a machine by name from the list of machines
    private Machine findMachineByName(String machineName, List<Machine> machines) {
        for (Machine machine : machines) {
            if (machine.getName().equals(machineName)) {
                return machine;
            }
        }
        System.err.println("Machine with name '" + machineName + "' not found.");
        return null;
    }

    // Main method for testing
    public static void main(String[] args) {
        InputParser parser = new InputParser();
        String filePath = "Input_One.txt";

        List<Machine> machines = parser.parseMachines(filePath);
        System.out.println("Machines:");
        for (Machine machine : machines) {
            System.out.println("Id: " + machine.getId() + ", Name: " + machine.getName() + ", Capacity: " + machine.getCapacity() + ", Cooldown Time: " + machine.getCooldownTime());
        }

        List<Part> parts = parser.parseParts(filePath);
        System.out.println("Parts:");
        for (Part part : parts) {
            System.out.println("Id: " + part.getId() + ", Name: " + part.getName() + ", Quantity: " + part.getQuantity());
        }

        List<Operation> operations = parser.parsePartOperations(filePath);
//        System.out.println("Part Operations:");
//        for (Operation operation : operations) {
//            System.out.println("Machine: " + operation.getMachine().getName() + ", Processing Time: " + operation.getProcessingTime());
//        }


        System.out.println("Parts operations:");
        for (Part part : parts) {
            System.out.println("Id: " + part.getId() + " ,Operations: ");
            for(Operation operation: part.getOperations())
                System.out.println(operation);
        }
    }
}