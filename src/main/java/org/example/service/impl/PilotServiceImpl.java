package org.example.service.impl;

import org.example.models.Pilot;
import org.example.repository.PilotRepository;
import org.example.service.PilotService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;

    public PilotServiceImpl(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    @Override
    public void findAll() throws SQLException {
        List<Pilot> allPilots = pilotRepository.findAll();

        System.out.println("List pilots:");
        for (Pilot pilot : allPilots) {
            System.out.println("Model( " + pilot.getName() + ") Serial number (" + pilot.getAge() + ")");
        }
    }

    @Override
    public void findById(Scanner scanner) throws SQLException {
        System.out.println("Enter the id:");
        int id = Integer.parseInt(scanner.nextLine());

        Pilot pilot = pilotRepository.findById(id);
        System.out.println("The airplane was successfully found:" + pilot);
    }

    @Override
    public void add(Scanner scanner) throws SQLException {
        System.out.println("Enter the pilot name:");
        String name = scanner.nextLine();

        System.out.println("Enter the pilot age:");
        short age = Short.parseShort(scanner.nextLine());

        Pilot pilot = new Pilot(-1, name, age);
        try {
            pilotRepository.save(pilot);
            System.out.println("Pilot added successfully");
        } catch (SQLException e) {
            System.out.println("Addition error: " + e.getMessage());
        }
    }

    @Override
    public void update(Scanner scanner) throws SQLException {
        System.out.println("\nEnter the pilot ID to update the information:");
        int id = scanner.nextInt();

        System.out.println("\nEnter the pilot name:");
        String name = scanner.nextLine();

        System.out.println("\nEnter the pilot age:");
        short age = Short.parseShort(scanner.nextLine());

        Pilot pilot = new Pilot(id, name, age);
        try {
            pilotRepository.update(pilot);
            System.out.println("\nPilot updated successfully.");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Scanner scanner) throws SQLException {
        System.out.println("\nEnter pilot ID to delete:");
        int id = scanner.nextInt();

        try {
            pilotRepository.deleteById(id);
            System.out.println("Pilot deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Deleting error: " + e.getMessage());
        }
    }

    @Override
    public void addPossiblePlane(Scanner scanner) {
        System.out.println("\nEnter pilot ID:");
        int pilotId = scanner.nextInt();

        System.out.println("\nEnter airplane model ID:");
        int modelId = scanner.nextInt();

        try {
            pilotRepository.addPossiblePlane(pilotId,modelId);
            System.out.println("The airplane model has been successfully added to the pilot available list");
        } catch (SQLException e) {
            System.out.println("Added error:" + e.getMessage());
        }
    }
}
