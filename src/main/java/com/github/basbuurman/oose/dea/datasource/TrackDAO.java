package com.github.basbuurman.oose.dea.datasource;

import com.github.basbuurman.oose.dea.datasource.datamappers.TrackMapper;
import com.github.basbuurman.oose.dea.datasource.util.ConnectionFactory;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrackDAO implements ITrackDAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    private ConnectionFactory connectionFactory;
    private TrackMapper trackMapper = new TrackMapper();

    @Inject
    public void setDatabaseConnection(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<TrackDTO> getAllTracks(int playlistId, String token) {
        List<TrackDTO> trackDTOS = new ArrayList<>();

        String sql = "SELECT * FROM tracks WHERE NOT EXISTS(SELECT * FROM playlistTrack pT WHERE tracks.trackId = pT.trackId AND pT.playlistId = ?)";

        try (Connection connection = connectionFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playlistId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
               trackDTOS.add(trackMapper.insert(resultSet));
            }

            statement.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }

        return trackDTOS;
    }
}