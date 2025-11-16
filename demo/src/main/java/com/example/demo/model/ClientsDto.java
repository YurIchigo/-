package com.example.demo.model;

public class ClientsDto {
    private int id;
    private String name;

    public ClientsDto(Clients clients) {
        this.id = clients.getId();
        this.name = clients.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
