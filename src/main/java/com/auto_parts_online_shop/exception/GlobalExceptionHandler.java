package com.auto_parts_online_shop.exception;

import com.auto_parts_online_shop.exception.NotFoundException.NotFoundException;
import com.auto_parts_online_shop.exception.NotFoundException.NotFoundExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<NotFoundExceptionMessage> handleNotFoundException(NotFoundException e) {
        NotFoundExceptionMessage mess = new NotFoundExceptionMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mess);
    }
}
