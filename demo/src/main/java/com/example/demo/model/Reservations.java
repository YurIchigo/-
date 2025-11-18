package com.example.demo.model;

public class Reservations {

    private int id;
    private int client_id;
    private int time_id;
    private String status;
    private String reason_delete;

    public Reservations(int id, int client_id, int time_id, String status, String reason_delete) {
        this.id = id;
        this.client_id = client_id;
        this.time_id = time_id;
        this.status = status;
        this.reason_delete = reason_delete;
    }

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int clients_id) {
        this.client_id = clients_id;
    }

    public int getTime_id() {
        return time_id;
    }

    public void setTime_id(int time_id) {
        this.time_id = time_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason_delete() {
        return reason_delete;
    }

    public void setReason_delete(String reason_delete) {
        this.reason_delete = reason_delete;
    }
}
