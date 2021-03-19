package com.github.basbuurman.oose.dea.datasource;

import com.github.basbuurman.oose.dea.datasource.datamappers.TokenMapper;
import com.github.basbuurman.oose.dea.datasource.util.ConnectionFactory;
import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;
import com.github.basbuurman.oose.dea.resources.dto.UserDTO;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO implements ILoginDAO {
    private Logger logger = Logger.getLogger(getClass().getName());
    private ConnectionFactory connectionFactory;
    private TokenMapper tokenMapper = new TokenMapper();

    @Inject
    public void setDatabaseConnection(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<TokenDTO> getUserToken(UserDTO userDTO) {
        String sql = "SELECT * FROM users WHERE user = ? AND password = ?";
        List<TokenDTO> tokenDTOS = new ArrayList<>();

        try (Connection connection = connectionFactory.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userDTO.getUser());
            statement.setString(2, userDTO.getPassword());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tokenDTOS.add(tokenMapper.insert(resultSet));
            }

            statement.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + connectionFactory.connectionString(), e);
        }

        return tokenDTOS;
    }
}