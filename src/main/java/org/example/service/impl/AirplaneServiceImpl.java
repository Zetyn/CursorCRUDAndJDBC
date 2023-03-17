package org.example.service.impl;


import org.example.models.Airplane;
import org.example.repository.AirplaneRepository;
import org.example.service.AirplaneService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public void findAll() throws SQLException {
        List<Airplane> allAirplanes = airplaneRepository.findAll();

        System.out.println("List airplanes:");
        for (Airplane airplane : allAirplanes) {
            System.out.println("Model( " + airplane.getModel() + ") Serial number (" + airplane.getSerialNumber() + ") Seats (" + airplane.getSeats() + ")");
        }
    }

    @Override
    public void findById(Scanner scanner) throws SQLException {
        System.out.println("Enter the id:");
        int id = Integer.parseInt(scanner.nextLine());

        Airplane airplane = airplaneRepository.findById(id);
        System.out.println("The airplane was successfully found:" + airplane);
    }

    @Override
    public void findBySerialNumber(Scanner scanner) throws SQLException {
        System.out.println("Enter the serial number:");
        String serialNumber = scanner.nextLine();

        Airplane airplane = airplaneRepository.findByIdSerialNumber(serialNumber);
        System.out.println("The airplane was successfully found:" + airplane);
    }

    @Override
    public void addAirplaneModel(Scanner scanner) {
        System.out.println("Enter the model name:");
        String model = scanner.nextLine();

        try {
            airplaneRepository.addAirplaneModel(model);
            System.out.println("Model added successfully");
        } catch (SQLException e) {
            System.out.println("Addition error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAirplaneModelById(Scanner scanner) {
        System.out.println("\nEnter the model id:");
        int id = scanner.nextInt();

        try {
            airplaneRepository.deleteAirplaneModelById(id);
            System.out.println("Airplane model deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Deleting error: " + e.getMessage());
        }
    }

    @Override
    public void add(Scanner scanner) throws SQLException {
        System.out.println("\nEnter the airplane model:");
        String model = scanner.nextLine();

        System.out.println("\nEnter the serial number:");
        String serialNumber = scanner.nextLine();

        System.out.println("\nEnter the number of seats:");
        int seats = scanner.nextInt();

        Airplane airplane = new Airplane(-1, model, serialNumber, seats);
        try {
            airplaneRepository.save(airplane);
            System.out.println("Airplane added successfully");
        } catch (SQLException e) {
            System.out.println("Addition error: " + e.getMessage());
        }
    }

    @Override
    public void update(Scanner scanner) {
        System.out.println("\nEnter the airplane ID to update the information:");
        int id = scanner.nextInt();

        System.out.println("\nEnter the airplane model:");
        String model = scanner.nextLine();

        System.out.println("\nEnter the serialNumber:");
        String serialNumber = scanner.nextLine();

        System.out.println("\nEnter the number of seats:");
        int seats = scanner.nextInt();

        Airplane airplane = new Airplane(id, model, serialNumber, seats);
        try {
            airplaneRepository.update(airplane);
            System.out.println("\nAirplane updated successfully.");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Scanner scanner) {
        System.out.println("\nEnter airplane ID to delete:");
        int id = scanner.nextInt();

        try {
            airplaneRepository.deleteById(id);
            System.out.println("Airplane deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Deleting error: " + e.getMessage());
        }
    }
}
