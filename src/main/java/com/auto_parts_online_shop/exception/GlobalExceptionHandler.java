package com.auto_parts_online_shop.exception;

import com.auto_parts_online_shop.exception.FormatException.FormatException;
import com.auto_parts_online_shop.exception.FormatException.FormatExceptionMessage;
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

    @ExceptionHandler({FormatException.class})
    public ResponseEntity<FormatExceptionMessage> handleFormatException(FormatException e) {
        FormatExceptionMessage mess = new FormatExceptionMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mess);
    }
}
