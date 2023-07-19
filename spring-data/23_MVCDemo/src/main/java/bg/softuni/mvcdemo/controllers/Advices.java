package bg.softuni.mvcdemo.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Advices {

    @ExceptionHandler
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        System.out.println(ex.getMessage());

        return ResponseEntity
                .notFound()
                .build();
    }
}
