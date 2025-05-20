package com.company.exception.header;


import com.company.configuration.MessageService;
import com.company.exception.BadRequestException;
import com.company.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {


    private final MessageService messageService;

    public CustomExceptionHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> handlerItemNotFoundException(ItemNotFoundException e) {
        log.error(e.getMessage());
        log.info("user qo`shildi");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(messageService.getMessage("item.not.found"));
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handlerBadRequestException(BadRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handlerPSQLException(ItemNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
