package com.example.demo.dao;

import com.example.demo.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BookingDao {

    private final Connection connection;

    public BookingDao(Connection connection){
        this.connection = connection;
    }

    public List<Clients> findAllClients() throws SQLException {
        List<Clients> clients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Clients(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")));
            }
        }
        return clients;
    }

   public Optional<Clients> findClientById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Clients(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")));
            }
            return Optional.empty();
        }
   }

    public boolean addClient (Clients clients) throws  SQLException {
        try (PreparedStatement psclient = connection.prepareStatement("INSERT INTO clients(name, phone, email) VALUES (?, ?, ?)")){
            psclient.setString(1, clients.getName());
            psclient.setString(2, clients.getPhone());
            psclient.setString(3, clients.getEmail());
            int rowsInserted = psclient.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public  boolean addReservation (Reservations reservations) throws  SQLException {
        try (PreparedStatement psReservation = connection.prepareStatement("INSERT INTO reservations(client_id, time_id) VALUES (?, ?)")){
            psReservation.setInt(1, reservations.getClient_id());
            psReservation.setObject(2, reservations.getTime_id());
            int rowsInserted = psReservation.executeUpdate();
            return  rowsInserted > 0;
        }
    }

    public int findTimeSlotsId (LocalDateTime dateTime) throws  SQLException {
        try (PreparedStatement psTimeSlotsId = connection.prepareStatement(
                "SELECT id FROM time_slots WHERE date=? AND time=?"
        )) {
            psTimeSlotsId.setObject(1, dateTime.toLocalDate());
            psTimeSlotsId.setObject(2, dateTime.toLocalTime());
            ResultSet resultSet = psTimeSlotsId.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new IllegalAccessException("Доступное время не найдено");
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Time_SlotsDto> findTimeSlotsFree(LocalDate date) throws SQLException {
        List<Time_SlotsDto> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT ts.date, ts.time, ts.count - COUNT(r.id) AS free_count " +
                        "FROM time_slots ts " +
                        "LEFT JOIN reservations r ON ts.id = r.time_id AND r.status = 'ACTIVE'" +
                        "WHERE ts.date = ? " +
                        "GROUP BY ts.id " +
                        "ORDER BY ts.time ASC")) {
            statement.setObject(1, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Time_SlotsDto slot = new Time_SlotsDto(
                        resultSet.getObject("time", LocalTime.class),
                        resultSet.getInt("free_count"));
                results.add(slot);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public List<ReservationsDto> getCountTimeSlot(LocalDate date) throws SQLException {
        List<ReservationsDto> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT ts.time, COUNT(r.id) AS reserved_count, " +
                "GROUP_CONCAT(CONCAT_WS(';', c.id, c.name, c.phone, c.email)) AS clients_list " +
                "FROM time_slots ts " +
                "INNER JOIN reservations r ON ts.id = r.time_id AND r.status = 'ACTIVE' " +
                "INNER JOIN clients c ON r.client_id = c.id " +
                "WHERE ts.date = ? " +
                "GROUP BY ts.time " +
                "ORDER BY ts.time ASC;")) {
            statement.setObject(1, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LocalDateTime datetime = resultSet.getObject("datetime", LocalDateTime.class);
                int count = resultSet.getInt("count");
                String clientList = resultSet.getString("client_list");

                List<Clients> clients = getClientsList(clientList);
                result.add(new ReservationsDto(datetime, count, clients));
            }
        }
        return result;
    }

    private List<Clients> getClientsList (String ListClient) {
        List<Clients> clients = new ArrayList<>();
        if (ListClient != null && ListClient.isEmpty()) {
            String[] client = ListClient.split(",");
            for (int i = 0; i < client.length; i +=4) {
                int id = Integer.parseInt(client[i]);
                String name = client[i + 1];
                String phone = client[i + 2];
                String email = client[i + 3];
                clients.add(new Clients(id, name, phone, email));
            }
        }
        return clients;
    }
}
