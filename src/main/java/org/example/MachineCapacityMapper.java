package org.example;

import java.util.HashMap;
import java.util.Map;

public class MachineCapacityMapper {
    private static final Map<String, Integer> capacityMap;

    static {
        capacityMap = new HashMap<>();
        capacityMap.put("one", 1);
        capacityMap.put("two", 2);
        capacityMap.put("three", 3);
        capacityMap.put("four", 4);
        // Add more mappings as needed
    }

    public static Integer getCapacity(String capacityString) {
        return capacityMap.getOrDefault(capacityString.toLowerCase(), 0); // Return 0 for unknown values
    }

    // Example usage:
    public static void main(String[] args) {
        // Test the mapping
        System.out.println("Capacity for 'one': " + getCapacity("one")); // Output: 1
        System.out.println("Capacity for 'two': " + getCapacity("two")); // Output: 2
        System.out.println("Capacity for 'unknown': " + getCapacity("unknown")); // Output: 0 (unknown value)
    }
}
