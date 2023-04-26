package ca.ulaval.glo4002.game.dinosaur.api.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.game.dinosaur.entities.exceptions.DinosaurNotFoundException;
import ca.ulaval.glo4002.game.shared.exceptions.ExceptionResponse;

@Provider
public class DinosaurNotFoundExceptionMapper implements ExceptionMapper<DinosaurNotFoundException> {
    private static final String ERROR_CODE = "NON_EXISTENT_NAME";
    private static final int HTTP_STATUS = 404;

    @Override
    public Response toResponse(DinosaurNotFoundException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.error = ERROR_CODE;
        exceptionResponse.description = exception.getMessage();

        return Response.status(HTTP_STATUS).entity(exceptionResponse).build();
    }
}
