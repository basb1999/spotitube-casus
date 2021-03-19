package com.github.basbuurman.oose.dea.resources.exceptionmappers;

import com.github.basbuurman.oose.dea.services.exceptions.AuthorizationHasFailedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationHasFailedExceptionMapper implements ExceptionMapper<AuthorizationHasFailedException> {

    @Override
    public Response toResponse(AuthorizationHasFailedException e) {
        return Response.status(401).build();
    }
}