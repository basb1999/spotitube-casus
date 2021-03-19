package com.github.basbuurman.oose.dea.resources.exceptionmappers;

import com.github.basbuurman.oose.dea.services.exceptions.RequestInvalidException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestInvalidExceptionMapper implements ExceptionMapper<RequestInvalidException> {

    @Override
    public Response toResponse(RequestInvalidException e) {
        return Response.status(400).build();
    }
}