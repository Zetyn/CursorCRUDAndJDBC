package org.example.repository.impl;

import org.example.ConnectionDB;
import org.example.models.Airplane;
import org.example.models.Pilot;
import org.example.repository.PilotRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotImpl implements PilotRepository {
    @Override
    public List<Pilot> findAll() throws SQLException {
        List<Pilot> pilots = new ArrayList<>();
        String query = "SELECT * FROM pilots";

        try (Connection connection = ConnectionDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Pilot pilot = new Pilot(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getShort("age")
                );
                pilots.add(pilot);
            }
        }

        return pilots;
    }

    @Override
    public Pilot findById(int id) throws SQLException {
        String query = "SELECT * FROM pilots WHERE id = ?";
        Pilot pilot = null;

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    pilot = new Pilot(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getShort("age")
                    );
                }
            }
        return pilot;
    }

    @Override
    public int save(Pilot pilot) throws SQLException {
        String query = "INSERT INTO pilots (name, age) VALUES (?, ?)";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pilot.getName());
            preparedStatement.setShort(2, pilot.getAge());
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
    public int addPossiblePlane(int pilotId, int airplaneModelId) throws SQLException {
        String query = "INSERT INTO pilot_airplane_models (pilot_id,airplane_model_id) VALUES(?,?)";
        try (Connection connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,pilotId);
            preparedStatement.setInt(2,airplaneModelId);
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
    public int update(Pilot pilot) throws SQLException {
        String query = "UPDATE pilots SET name = ?, age = ? WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, pilot.getName());
            preparedStatement.setShort(2, pilot.getAge());
            preparedStatement.setInt(3, pilot.getId());
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int deleteById(int id) throws SQLException {
        String query = "DELETE FROM pilots WHERE id = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
    }
}
