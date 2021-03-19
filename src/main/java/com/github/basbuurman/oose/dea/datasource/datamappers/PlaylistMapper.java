package com.github.basbuurman.oose.dea.datasource.datamappers;

import com.github.basbuurman.oose.dea.resources.dto.PlaylistDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistMapper {
    public PlaylistDTO insert(ResultSet resultSet) throws SQLException {
        PlaylistDTO playlist = new PlaylistDTO();

        try {
            playlist.setId(resultSet.getInt("playlistId"));
            playlist.setName(resultSet.getString("name"));
            playlist.setOwner(resultSet.getBoolean("owner"));
            playlist.setTracks(null);

        } catch (SQLException e) {
            throw new SQLException();
        }

        return playlist;
    }
}
