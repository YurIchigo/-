package com.example.demo.service;

import com.example.demo.dao.BookingDao;
import com.example.demo.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {

    private final BookingDao bookingDao;

    public BookingService(Connection connection) {
        this.bookingDao = new BookingDao(connection);
    }

    public List<ClientsDto> findAllClientsAsDto() throws SQLException {
        List<Clients> allClients = bookingDao.findAllClients();
        return allClients.stream()
                .map(ClientsDto::new)
                .collect(Collectors.toList());
    }

    public Optional<Clients> getClientById(int id) throws SQLException {
        return bookingDao.findClientById(id);
    }

    public boolean registerNewClient(Clients clients) throws SQLException {
        return bookingDao.addClient(clients);
    }

    public boolean updateNewClient(Clients clients) throws SQLException {
        return bookingDao.updateClient(clients);
    }

    public int newReservation(ReservedDto reservedDto) throws  SQLException {
        return  bookingDao.addReservation(reservedDto);
    }

    public boolean deleteReserved(ReservedDeleteDto reservedDeleteDto) throws  SQLException {
        return bookingDao.deleteReservation(reservedDeleteDto);
    }

    public List<Time_SlotsDto> getFreeTimeSlots(LocalDate date) throws SQLException {
        return bookingDao.findTimeSlotsFree(date);
    }

    public List<ReservationsDto> getCountTimeSlot(LocalDate date) throws SQLException {
        return  bookingDao.getCountTimeSlot(date);
    }
}
