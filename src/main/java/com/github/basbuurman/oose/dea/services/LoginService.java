package com.github.basbuurman.oose.dea.services;

import com.github.basbuurman.oose.dea.datasource.ILoginDAO;
import com.github.basbuurman.oose.dea.resources.dto.TokenDTO;
import com.github.basbuurman.oose.dea.resources.dto.UserDTO;
import com.github.basbuurman.oose.dea.services.exceptions.AuthorizationHasFailedException;

import javax.inject.Inject;
import java.util.List;

public class LoginService implements ILoginService {
    private ILoginDAO loginDAO;

    @Inject
    public void setLoginDAO(ILoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public TokenDTO login(UserDTO userDTO) {
        List<TokenDTO> tokenDTOS = loginDAO.getUserToken(userDTO);

        if (tokenDTOS.isEmpty()) {
            throw new AuthorizationHasFailedException();
        } else {
            return tokenDTOS.get(0);
        }
    }
}