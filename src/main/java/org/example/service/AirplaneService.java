package org.example.service;

import java.sql.SQLException;
import java.util.Scanner;

public interface AirplaneService extends BasicCRUDOperations{
    void findBySerialNumber(Scanner scanner) throws SQLException;
    void addAirplaneModel(Scanner scanner) throws SQLException;
    void deleteAirplaneModelById(Scanner scanner) throws  SQLException;
}
