package com.bank.transaction.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeign(
            FeignException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.contentUTF8());
    }
}