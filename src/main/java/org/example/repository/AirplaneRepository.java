package org.example.repository;

import org.example.models.Airplane;

import java.sql.SQLException;
import java.util.List;

public interface AirplaneRepository {
    Airplane findById(int id) throws SQLException;
    Airplane findByIdSerialNumber(String serialNumber) throws SQLException;
    List<Airplane> findAll() throws  SQLException;
    int addAirplaneModel(String model) throws SQLException;
    int save(Airplane airplane) throws SQLException;
    int update(Airplane airplane) throws SQLException;
    int deleteById(int id) throws SQLException;
    int deleteAirplaneModelById(int id) throws SQLException;
}
