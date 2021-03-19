package com.github.basbuurman.oose.dea.datasource.datamappers;

import com.github.basbuurman.oose.dea.resources.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackMapper {
    public TrackDTO insert(ResultSet resultSet) throws SQLException {
        TrackDTO track = new TrackDTO();

        try {
            track.setId(resultSet.getInt("trackId"));
            track.setTitle(resultSet.getString("title"));
            track.setPerformer(resultSet.getString("performer"));
            track.setDuration(resultSet.getInt("duration"));
            track.setAlbum(resultSet.getString("album"));
            track.setPlaycount(resultSet.getInt("playcount"));
            track.setPublicationDate(resultSet.getString("publicationDate"));
            track.setDescription(resultSet.getString("description"));
            track.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));

        } catch (SQLException e) {
            throw new SQLException();
        }

        return track;
    }
}
