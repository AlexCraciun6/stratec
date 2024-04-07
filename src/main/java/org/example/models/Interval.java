package org.example.models;

import java.time.LocalTime;

public class Interval {
    private LocalTime start;
    private LocalTime end;

    public Interval(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(LocalTime startTime, LocalTime endTime) {
        return !startTime.isAfter(end) && !start.isAfter(endTime);
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    // Getters, setters, other methods
}
