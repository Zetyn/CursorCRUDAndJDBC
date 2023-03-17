package org.example.service;

import java.sql.SQLException;
import java.util.Scanner;

public interface BasicCRUDOperations {
    void findAll() throws SQLException;
    void findById(Scanner scanner) throws SQLException;
    void add(Scanner scanner) throws SQLException;
    void update(Scanner scanner) throws SQLException;
    void deleteById(Scanner scanner) throws SQLException;
}
