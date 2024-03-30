package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Part {
    private String name;
    private int quantity;
    private List<Operation> operations;

    public Part(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.operations = new ArrayList<>();
    }

    public void addOperation(Operation operation) {
        operations.add(operation);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}