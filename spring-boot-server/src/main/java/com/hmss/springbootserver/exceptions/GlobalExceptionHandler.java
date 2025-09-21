package com.hmss.springbootserver.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyInUseException(EmailAlreadyInUseException exception, WebRequest request){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(exception.getMessage(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, LocalDateTime.now(), request.getDescription(false));
        return new ResponseEntity<ErrorResponseDTO>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PasswordsNotMatchingException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyInUseException(PasswordsNotMatchingException exception, WebRequest request){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, LocalDateTime.now(), request.getDescription(false));
        return new ResponseEntity<ErrorResponseDTO>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(ResourceNotFoundException exception, WebRequest request){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(exception.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, LocalDateTime.now(), request.getDescription(false));
        return new ResponseEntity<ErrorResponseDTO>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorResponseDTO> handleIncorrectPasswordException(IncorrectPasswordException exception, WebRequest request){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(exception.getMessage(), HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, LocalDateTime.now(), request.getDescription(false));
        return new ResponseEntity<ErrorResponseDTO>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponseDTO> handleFileStorageException(FileStorageException exception, WebRequest request){
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), request.getDescription(false));
        return new ResponseEntity<ErrorResponseDTO>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException exception, WebRequest request) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String errorMessages = "Errors (" + fieldErrors.size() + "): " +
                fieldErrors.stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                errorMessages,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
