package org.example;


import org.example.repository.AirplaneRepository;
import org.example.repository.PilotRepository;
import org.example.repository.impl.AirplaneRepositoryImpl;
import org.example.repository.impl.PilotImpl;
import org.example.service.AirplaneService;
import org.example.service.PilotService;
import org.example.service.impl.AirplaneServiceImpl;
import org.example.service.impl.PilotServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args ) throws SQLException {
        AirplaneRepository airplaneRepository = new AirplaneRepositoryImpl();
        AirplaneService airportService = new AirplaneServiceImpl(airplaneRepository);
        PilotRepository pilotRepository = new PilotImpl();
        PilotService pilotService = new PilotServiceImpl(pilotRepository);
        boolean exit;
        Scanner scanner = new Scanner(System.in);
        System.out.println("""

                Hi here is the list of available commands to use the command type /command name:
                /list airplanes(shows all planes)
                /list pilots(shows all pilots)
                /find airplane by id
                /find pilot by id
                /find by sn(Find airplane by serial number)
                /add airplane(Adds a new airplane)
                /add airplane model
                /add pilot(Adds a new pilot)
                /add possible airplane model
                /update airplane(Updates information about an existing airplane)
                /update pilot(Updates information about an existing pilot)
                /delete airplane(Removes the airplane)
                /delete pilot(Removes the pilot)
                /exit(Terminates the program)
                Note, if the airplane model does not yet exist in the list, add it first and then add the airplane
                """);
        String command;
        command = scanner.nextLine();
        do {
            switch (command) {
                case "/list airplanes":
                    airportService.findAll();
                    command = scanner.nextLine();
                    break;
                case "/list pilots":
                    pilotService.findAll();
                    command = scanner.nextLine();
                    break;
                case "/find airplane by sn":
                    airportService.findBySerialNumber(scanner);
                    command = scanner.nextLine();
                    break;
                case "/find airplane by id":
                    airportService.findById(scanner);
                    command = scanner.nextLine();
                    break;
                case "/find pilot by id":
                    pilotService.findById(scanner);
                    command = scanner.nextLine();
                    break;
                case "/add airplane":
                    airportService.add(scanner);
                    command = scanner.nextLine();
                    break;
                case "/add airplane model":
                    airportService.addAirplaneModel(scanner);
                    command = scanner.nextLine();
                    break;
                case "/add pilot":
                    pilotService.add(scanner);
                    command = scanner.nextLine();
                    break;
                case "/add possible airplane model":
                    pilotService.addPossiblePlane(scanner);
                    command = scanner.nextLine();
                    break;
                case "/update airplane":
                    airportService.update(scanner);
                    command = scanner.nextLine();
                    break;
                case "/update pilot":
                    pilotService.update(scanner);
                    command = scanner.nextLine();
                    break;
                case "/delete airplane":
                    airportService.deleteById(scanner);
                    command = scanner.nextLine();
                    break;
                case "/delete airplane model":
                    airportService.deleteAirplaneModelById(scanner);
                    command = scanner.nextLine();
                    break;
                case "/delete pilot":
                    pilotService.deleteById(scanner);
                    command = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
                    break;
            }
            exit = command.equals("/exit");
        } while (!exit);
    }
}
