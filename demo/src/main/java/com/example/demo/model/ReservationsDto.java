package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationsDto {
    private LocalDateTime datetime;
    private int count;
    private List<Clients> clients;

    public ReservationsDto(LocalDateTime datetime, int count, List<Clients> clients) {
        this.datetime = datetime;
        this.count = count;
        this.clients = clients;
    }

    public void setDateTime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public List<Clients> getClients() {
        return clients;
    }

    public int getCount() {
        return count;
    }
}
