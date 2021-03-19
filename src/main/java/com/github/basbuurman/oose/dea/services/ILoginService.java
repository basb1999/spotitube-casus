package com.github.basbuurman.oose.dea.services;

import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;
import com.github.basbuurman.oose.dea.resources.dto.UserDTO;

public interface ILoginService {
    TokenDTO login(UserDTO userDTO);
}