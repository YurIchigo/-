package com.example.demo.data;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingData {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tester",
                "root", "12345678");

        try (connection){
            createTables(connection);
            generateTimeSlots(connection);
            System.out.println("Таблицы успешно созданы.");
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при создании таблиц." + e.getMessage());
        }

    }

    public static void createTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createClientsTable =
                                    "CREATE TABLE IF NOT EXISTS clients (" +
                                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                                    "name VARCHAR(255) NOT NULL," +
                                    "phone VARCHAR(12) UNIQUE NOT NULL," +
                                    "email VARCHAR(255) UNIQUE NOT NULL" +
                                    ");";

            String createTimeSlotsTable =
                                    "CREATE TABLE IF NOT EXISTS time_slots (" +
                                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                                    "date DATE NOT NULL," +
                                    "time TIME NOT NULL," +
                                    "count INT DEFAULT 10," +
                                    "UNIQUE (date, time)" +
                                    ");";

            String createReservationsTable =
                                    "CREATE TABLE IF NOT EXISTS reservations (" +
                                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                                    "client_id INT NOT NULL," +
                                    "time_id INT NOT NULL," +
                                    "status VARCHAR(20) DEFAULT 'ACTIVE'," +
                                    "reason_delete VARCHAR(255)," +
                                    "FOREIGN KEY (client_id) REFERENCES clients(id)," +
                                    "FOREIGN KEY (time_id) REFERENCES time_slots(id)" +
                                    ");";

            statement.executeUpdate(createClientsTable);
            statement.executeUpdate(createTimeSlotsTable);
            statement.executeUpdate(createReservationsTable);
        }
    }

    public static void generateTimeSlots(Connection connection) throws SQLException {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(10);

        for (LocalDate localDate = startDate; !localDate.isAfter(endDate); localDate = localDate.plusDays(1)) {
            generateDateTime(connection, localDate);
        }
    }

    public static void generateDateTime(Connection connection, LocalDate date) throws  SQLException {
        LocalTime startTime = LocalTime.parse("10:00");
        LocalTime endTime = LocalTime.parse("20:00");

        while ( !startTime.isAfter(endTime)) {
            generateTime(connection, date, startTime);
            startTime = startTime.plusHours(1);
        }

    }

    public static void generateTime (Connection connection, LocalDate date, LocalTime time) throws SQLException {
        String generateDataTime = """
                INSERT INTO time_slots (date, time, count)
                VALUES (?, ?, ?)
                ON DUPLICATE KEY UPDATE count = count
                """;
        try (PreparedStatement statement = connection.prepareStatement(generateDataTime)) {
            statement.setObject(1, date);
            statement.setObject(2, time);
            statement.setInt(3, 10);
            statement.executeUpdate();

        }
    }

}
