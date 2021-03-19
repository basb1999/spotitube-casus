package com.github.basbuurman.oose.dea.datasource;

import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;
import com.github.basbuurman.oose.dea.resources.dto.UserDTO;

import java.util.List;

public interface ILoginDAO {
    List<TokenDTO> getUserToken(UserDTO userDTO);
}