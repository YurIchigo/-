package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ReservationsDto {
    private LocalTime time;
    private int count;
    private List<Clients> clients;

    public ReservationsDto(LocalTime time, int count, List<Clients> clients) {
        this.time = time;
        this.count = count;
        this.clients = clients;
    }

    public void setTime(LocalTime datetime) {
        this.time = datetime;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalTime gettime() {
        return time;
    }

    public List<Clients> getClients() {
        return clients;
    }

    public int getCount() {
        return count;
    }
}
