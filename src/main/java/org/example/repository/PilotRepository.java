package org.example.repository;

import org.example.models.Pilot;

import java.sql.SQLException;
import java.util.List;

public interface PilotRepository {
    List<Pilot> findAll() throws SQLException;
    Pilot findById(int id) throws SQLException;
    int save(Pilot pilot) throws SQLException;
    int addPossiblePlane(int pilotId,int airplaneModelId) throws SQLException;
    int update(Pilot pilot) throws SQLException;
    int deleteById(int id) throws SQLException;
}
