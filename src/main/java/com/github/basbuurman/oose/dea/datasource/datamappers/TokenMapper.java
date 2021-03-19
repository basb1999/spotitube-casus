package com.github.basbuurman.oose.dea.datasource.datamappers;

import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper {
    public TokenDTO insert(ResultSet resultSet) throws SQLException {
        TokenDTO token = new TokenDTO();

        try {
            token.setToken(resultSet.getString("token"));
            token.setUser(resultSet.getString("name"));

        } catch (SQLException e) {
            throw new SQLException();
        }

        return token;
    }
}