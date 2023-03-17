package org.example.repository.impl;

import org.example.ConnectionDB;
import org.example.models.Airplane;
import org.example.repository.AirplaneRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirplaneRepositoryImpl implements AirplaneRepository {
    @Override
    public Airplane findById(int id) throws SQLException {
        String query = "SELECT * FROM airplanes INNER JOIN airplane_models am on am.id = airplanes.airplane_model_id WHERE id = ?";
        Airplane airplane = null;

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                airplane = new Airplane(
                        resultSet.getInt("id"),
                        resultSet.getString("model_name"),
                        resultSet.getString("serial_number"),
                        resultSet.getInt("seats")
                );
            }
        }

        return airplane;
    }

    @Override
    public Airplane findByIdSerialNumber(String serialNumber) throws SQLException {
        String query = "SELECT * FROM airplanes INNER JOIN airplane_models am on am.id = airplanes.airplane_model_id WHERE serial_number = ?";
        Airplane airplane = null;

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, serialNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                airplane = new Airplane(
                        resultSet.getInt("id"),
                        resultSet.getString("model_name"),
                        resultSet.getString("serial_number"),
                        resultSet.getInt("seats")
                );
            }
        }

        return airplane;
    }

    @Override
    public List<Airplane> findAll() throws SQLException {
        List<Airplane> airplanes = new ArrayList<>();
        String query = "SELECT * FROM airplanes INNER JOIN airplane_models am on am.id = airplanes.airplane_model_id";

        try (Connection connection = ConnectionDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Airplane airplane = new Airplane(
                        resultSet.getInt("id"),
                        resultSet.getString("model_name"),
                        resultSet.getString("serial_number"),
                        resultSet.getInt("seats")
                );
                airplanes.add(airplane);
            }
        }

        return airplanes;
    }

    @Override
    public int addAirplaneModel(String model) throws SQLException {
        String query = "INSERT INTO airplane_models (model_name) VALUES(?)";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, model);
            preparedStatement.executeQuery();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return -1;
            }
        }
    }

    @Override
    public int save(Airplane airplane) throws SQLException {
        String query = "INSERT INTO airplanes (airplane_model_id, serial_number, seats) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, Integer.parseInt(airplane.getModel()));
            preparedStatement.setString(2, airplane.getSerialNumber());
            preparedStatement.setInt(3, airplane.getSeats());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return -1;
            }
        }
    }

    @Override
    public int update(Airplane airplane) throws SQLException {
        String query = "UPDATE airplanes SET airplane_model_id = ?, seats = ? WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, airplane.getModel());
            preparedStatement.setInt(2, airplane.getSeats());
            preparedStatement.setInt(3, airplane.getId());
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int deleteById(int id) throws SQLException {
        String query = "DELETE FROM airplanes WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int deleteAirplaneModelById(int id) throws SQLException {
        String query = "DELETE FROM airplane_models WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
    }
}
