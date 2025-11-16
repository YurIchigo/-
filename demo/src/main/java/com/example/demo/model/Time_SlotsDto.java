package com.example.demo.model;

import java.time.LocalTime;

public class Time_SlotsDto {
    private LocalTime time;
    private int count;

    public Time_SlotsDto(LocalTime time, int count) {
        this.time = time;
        this.count = count;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
