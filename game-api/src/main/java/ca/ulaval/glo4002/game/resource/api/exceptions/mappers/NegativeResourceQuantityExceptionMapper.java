package ca.ulaval.glo4002.game.resource.api.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.game.resource.api.exceptions.NegativeResourceQuantityException;
import ca.ulaval.glo4002.game.shared.exceptions.ExceptionResponse;

@Provider
public class NegativeResourceQuantityExceptionMapper implements ExceptionMapper<NegativeResourceQuantityException> {
    private static final String ERROR_CODE = "INVALID_RESOURCE_QUANTITY";
    private static final int HTTP_STATUS = 400;

    @Override
    public Response toResponse(NegativeResourceQuantityException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = ERROR_CODE;
        exceptionResponse.description = exception.getMessage();

        return Response.status(HTTP_STATUS).entity(exceptionResponse).build();
    }
}
