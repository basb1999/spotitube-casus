package com.github.basbuurman.oose.dea.datasource;

import com.github.basbuurman.oose.dea.datasource.datamappers.PlaylistMapper;
import com.github.basbuurman.oose.dea.datasource.datamappers.TrackMapper;
import com.github.basbuurman.oose.dea.datasource.util.ConnectionFactory;
import com.github.basbuurman.oose.dea.resources.dto.PlaylistDTO;
import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaylistDAO implements IPlaylistDAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    private ConnectionFactory connectionFactory;
    private PlaylistMapper playlistMapper = new PlaylistMapper();
    private TrackMapper trackMapper = new TrackMapper();

    @Inject
    public void setDatabaseConnection(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<PlaylistDTO> getAllPlaylists(String token) {
        String sql = "SELECT * FROM playlists";
        List<PlaylistDTO> playlistDTOS = new ArrayList<>();

        try (Connection connection = connectionFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                playlistDTOS.add(playlistMapper.insert(resultSet));
            }

            statement.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }

        return playlistDTOS;
    }

    @Override
    public void addPlaylist(PlaylistDTO playlistDTO, String token) {
        try (Connection connection = connectionFactory.createConnection()) {
            String sql1 = "INSERT INTO playlists (name, owner) VALUES (?, true)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, playlistDTO.getName());
            statement1.executeUpdate();
            statement1.close();

            String user = "";
            String sql2 = "SELECT user FROM users WHERE token = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, token);
            ResultSet resultSet = statement2.executeQuery();

            if (resultSet.next()) {
                user = resultSet.getString("user");
            }

            statement2.close();

            int playlistId = -1;
            String sql3 = "SELECT playlistId FROM playlists WHERE name = ?";
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            statement3.setString(1, playlistDTO.getName());
            resultSet = statement3.executeQuery();

            if (resultSet.next()) {
                playlistId = resultSet.getInt("playlistId");
            }

            statement3.close();

            String sql4 = "INSERT INTO playlistUser VALUES (?, ?)";
            PreparedStatement statement4 = connection.prepareStatement(sql4);
            statement4.setInt(1, playlistId);
            statement4.setString(2, user);
            statement4.executeUpdate();
            statement4.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }
    }

    @Override
    public void editPlaylist(int playlistId, PlaylistDTO playlistDTO, String token) {
        try (Connection connection = connectionFactory.createConnection()) {
            String sql = "UPDATE playlists SET name = ? WHERE playlistId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, playlistDTO.getName());
            statement.setInt(2, playlistId);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }
    }

    @Override
    public void deletePlaylist(int playlistId, String token) {
        try (Connection connection = connectionFactory.createConnection()) {
            String sql1 = "DELETE FROM playlistUser WHERE playlistId = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, playlistId);
            statement1.executeUpdate();
            statement1.close();

            String sql2 = "DELETE FROM playlistTrack WHERE playlistId = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setInt(1, playlistId);
            statement2.executeUpdate();
            statement2.close();

            String sql3 = "DELETE FROM playlists WHERE playlistId = ?";
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            statement3.setInt(1, playlistId);
            statement3.executeUpdate();
            statement3.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }
    }

    @Override
    public List<TrackDTO> getAllTracksFromPlaylist(int playlistId, String token) {
        List<TrackDTO> trackDTOS = new ArrayList<>();

        try (Connection connection = connectionFactory.createConnection()) {
            String sql = "SELECT t.trackId AS 'trackId', title, performer, duration, album, playcount, publicationDate, description, offlineAvailable FROM playlists p INNER JOIN playlistTrack pT on p.playlistId = pT.playlistId INNER JOIN tracks t on pT.trackId = t.trackId WHERE p.playlistId = ?";
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

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO trackDTO, String token) {
        try (Connection connection = connectionFactory.createConnection()) {
            String sql1 = "INSERT INTO playlistTrack VALUES (?, ?)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, playlistId);
            statement1.setInt(2, trackDTO.getId());
            statement1.executeUpdate();
            statement1.close();

            String sql2 = "UPDATE tracks SET offlineAvailable = ? WHERE trackId = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setBoolean(1, trackDTO.isOfflineAvailable());
            statement2.setInt(2, trackDTO.getId());
            statement2.executeUpdate();
            statement2.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId, String token) {
        try (Connection connection = connectionFactory.createConnection()) {
            String sql = "DELETE FROM playlistTrack WHERE playlistId = ? AND trackId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }
    }

    @Override
    public int calculateTotalDurationPlaylists() {
        int totalDurationPlaylists = 0;

        try (Connection connection = connectionFactory.createConnection()) {
            String sql = "SELECT SUM(t.duration) AS 'totalDurationPlaylists' FROM tracks t INNER JOIN playlistTrack pT on t.trackId = pT.trackId";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalDurationPlaylists = resultSet.getInt("totalDurationPlaylists");
            }

            statement.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }

        return totalDurationPlaylists;
    }

    @Override
    public boolean checkToken(String token) {
        boolean validToken = false;

        try (Connection connection = connectionFactory.createConnection()) {
            String sql = "SELECT * FROM users WHERE token = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                validToken = true;
            }

            statement.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }

        return validToken;
    }
}