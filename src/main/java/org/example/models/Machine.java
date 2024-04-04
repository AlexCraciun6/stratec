package org.example.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Machine {
    private static int idCache = 0;
    private final int id;
    private String name;
    private Integer capacity;
    private Integer cooldownTime;
    private List<Interval> busyIntervals;

    public Machine(String name, Integer capacity, Integer cooldownTime) {
        this.id = idCache;
        idCache += 1;
        this.name = name;
        this.capacity = capacity;
        this.cooldownTime = cooldownTime;
        this.busyIntervals = new ArrayList<>();
    }
    public void addBusyInterval(Interval interval) {
        busyIntervals.add(interval);
    }
    public boolean isAvailable(LocalTime startTime, LocalTime endTime) {
        for (Interval interval : busyIntervals) {
            if (interval.overlaps(startTime, endTime)) {
                return false; // Machine is busy during the specified interval
            }
        }
        return true; // Machine is available during the specified interval
    }

    public List<Interval> getBusyIntervals() {
        return busyIntervals;
    }

    public void setBusyIntervals(List<Interval> busyIntervals) {
        this.busyIntervals = busyIntervals;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(Integer cooldownTime) {
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
