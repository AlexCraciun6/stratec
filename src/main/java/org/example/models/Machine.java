package org.example.models;

public class Machine {
    private static int idCache = 0;
    private final int id;
    private String name;
    private String capacity;
    private String cooldownTime;

    public Machine(String name, String capacity, String cooldownTime) {
        this.id = idCache;
        idCache += 1;
        this.name = name;
        this.capacity = capacity;
        this.cooldownTime = cooldownTime;
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

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(String cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity='" + capacity + '\'' +
                ", cooldownTime='" + cooldownTime + '\'' +
                '}';
    }
}
