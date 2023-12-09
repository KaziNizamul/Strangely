package com.strangely.backend.Config.Exception;

import com.strangely.backend.Model.Exception.exceptionDTO;
import com.strangely.backend.Service.Exception.Restexception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class exceptionHandler {

    @ExceptionHandler(value = { Restexception.class })
    @ResponseBody
    public ResponseEntity<exceptionDTO> handleException(Restexception ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new exceptionDTO(ex.getMessage()));
    }
}
