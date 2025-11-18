package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/v0/pool")
public class BookingController {

    private final BookingService bookingService;

    public BookingController() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tester", "root", "12345678");
        this.bookingService = new BookingService(connection);
    }

    @GetMapping("/main")
    public String mainlissener() {
        return "Hello World";
    }

    @GetMapping("/clients")
    public List<ClientsDto> listClients() throws SQLException {
        return bookingService.findAllClientsAsDto();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Clients> getClientId(@PathVariable int id) {
        try {
            Optional<Clients> clientsOptional = bookingService.getClientById(id);
            if (clientsOptional.isPresent()) {
                return  ResponseEntity.ok(clientsOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<?> addClient(@RequestBody Clients clients) {
        try {
            boolean newClient = bookingService.registerNewClient(clients);
            if (newClient) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Ошибка добавления клиента");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка базы данных: " + e.getMessage());
        }
    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<Clients> patchClients(@PathVariable int id, @RequestBody Clients clients) {
        try {
            Optional<Clients> optionalClients = bookingService.getClientById(id);

            if (!optionalClients.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Clients upClient = optionalClients.get();
            upClient.setName(clients.getName());
            upClient.setPhone(clients.getPhone());
            upClient.setEmail(clients.getEmail());

            boolean updateDataClient = bookingService.updateNewClient(upClient);
            if (updateDataClient) {
                return ResponseEntity.ok(upClient);
            } else  {
                return ResponseEntity.badRequest().build();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/reservations")
    public ResponseEntity<?> addReservation(@RequestBody ReservedDto reservedDto) {
        try {
            int newReservationId = bookingService.newReservation(reservedDto);
            return ResponseEntity.ok(Map.of("id", newReservationId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/time-slots/available")
    public ResponseEntity<List<Time_SlotsDto>> getFreeDataTime(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws SQLException {
        List<Time_SlotsDto> time_slots = bookingService.getFreeTimeSlots(date);
        return ResponseEntity.ok(time_slots);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationsDto>> getCountTimeSlot(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws  SQLException {
        List<ReservationsDto> data = bookingService.getCountTimeSlot(date);
        return ResponseEntity.ok(data);
    }

    @PostMapping("reservations/{id}/cancel")
    public ResponseEntity<ReservedDeleteDto> updateReservation(@PathVariable int id, @RequestBody ReservedDeleteDto reservedDeleteDto) {
        try {
            reservedDeleteDto.setClientId(id);

            boolean deleteReserved = bookingService.deleteReserved(reservedDeleteDto);

            if (deleteReserved) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
