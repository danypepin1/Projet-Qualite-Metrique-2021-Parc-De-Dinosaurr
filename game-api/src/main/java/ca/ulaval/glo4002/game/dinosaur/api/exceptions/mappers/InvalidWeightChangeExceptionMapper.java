package ca.ulaval.glo4002.game.dinosaur.api.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.InvalidWeightChangeException;
import ca.ulaval.glo4002.game.shared.exceptions.ExceptionResponse;

@Provider
public class InvalidWeightChangeExceptionMapper implements ExceptionMapper<InvalidWeightChangeException> {
    private static final String ERROR_CODE = "INVALID_WEIGHT_CHANGE";
    private static final int HTTP_STATUS = 400;

    @Override
    public Response toResponse(InvalidWeightChangeException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = ERROR_CODE;
        exceptionResponse.description = exception.getMessage();

        return Response.status(HTTP_STATUS).entity(exceptionResponse).build();
    }
}
