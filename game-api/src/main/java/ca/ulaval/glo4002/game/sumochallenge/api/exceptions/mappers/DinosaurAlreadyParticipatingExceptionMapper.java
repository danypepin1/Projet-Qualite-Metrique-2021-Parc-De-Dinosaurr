package ca.ulaval.glo4002.game.sumochallenge.api.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.game.shared.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.sumochallenge.entities.exceptions.DinosaurAlreadyParticipatingException;

@Provider
public class DinosaurAlreadyParticipatingExceptionMapper
        implements ExceptionMapper<DinosaurAlreadyParticipatingException> {
    private static final String ERROR_CODE = "DINOSAUR_ALREADY_PARTICIPATING";
    private static final int HTTP_STATUS = 400;

    @Override
    public Response toResponse(DinosaurAlreadyParticipatingException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = ERROR_CODE;
        exceptionResponse.description = exception.getMessage();

        return Response.status(HTTP_STATUS).entity(exceptionResponse).build();
    }
}
