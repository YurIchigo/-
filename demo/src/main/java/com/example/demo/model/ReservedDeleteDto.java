package com.example.demo.model;

public class ReservedDeleteDto {

    private int clientId;
    private String reason_delete;

    public ReservedDeleteDto(int clientId, String reason_delete) {
        this.clientId = clientId;
        this.reason_delete = reason_delete;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getReason_delete() {
        return reason_delete;
    }

    public void setReason_delete(String reason_delete) {
        this.reason_delete = reason_delete;
    }
}
