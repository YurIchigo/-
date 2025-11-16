package com.example.demo.model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Time_Slots {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private  int count;

    public Time_Slots(int id, LocalDate date, LocalTime time, int count) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setIdT(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
