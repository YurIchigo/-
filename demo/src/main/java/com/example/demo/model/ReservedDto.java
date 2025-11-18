package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ReservedDto {

    private int id;
    private int clientId;
    private LocalDate date;
    private LocalTime time;


    public ReservedDto(int id, int clientId, LocalDate date, LocalTime time) {
        this.id = id;
        this.clientId = clientId;
        this.date = date;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    public int getClientId() {
        return clientId;
    }
}


