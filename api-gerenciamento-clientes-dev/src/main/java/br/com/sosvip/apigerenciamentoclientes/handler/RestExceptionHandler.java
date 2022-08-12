package br.com.sosvip.apigerenciamentoclientes.handler;

import br.com.sosvip.apigerenciamentoclientes.exception.BadRequestException;
import br.com.sosvip.apigerenciamentoclientes.exception.InternalServerErrorException;
import br.com.sosvip.apigerenciamentoclientes.exception.RequestExceptionDetails;
import br.com.sosvip.apigerenciamentoclientes.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String, String> handlerIvalidArgument(MethodArgumentNotValidException e) {
        Map<String, String> errorMap=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(
                RequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(bre.getMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );

    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<RequestExceptionDetails> handlerInternalServerErrorException(InternalServerErrorException isee) {
        return new ResponseEntity<>(
                RequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .details(isee.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RequestExceptionDetails> handlerResourceNotFoundException(ResourceNotFoundException rnfe) {
        return new ResponseEntity<>(
                RequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(rnfe.getMessage())
                        .build(), HttpStatus.NOT_FOUND
        );
    }
}
