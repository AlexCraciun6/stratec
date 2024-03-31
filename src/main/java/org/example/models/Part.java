package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Part {
    private static int idCache = 0;
    private final int id;
    private String name;
    private int quantity;
    private List<Operation> operations;

    public Part(String name, int quantity) {
        this.id = idCache;
        idCache += 1;
        this.name = name;
        this.quantity = quantity;
        this.operations = new ArrayList<>();
    }

    public void addOperation(Operation operation) {
        operations.add(operation);
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
//                ", operations=" + operations +
                '}';
    }
}