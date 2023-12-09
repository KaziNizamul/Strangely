package com.strangely.backend.UnitTest.Service.Auth;

import com.strangely.backend.Service.Exception.Restexception;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestExceptionTest {

    @Test
    public void testRestExceptionCreation() {
        String message = "Sample error message";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Restexception restException = new Restexception(message, status);

        assertEquals(message, restException.getMessage());
        assertEquals(status, restException.getStatus());
    }

    @Test
    public void testRestExceptionWithNullStatus() {
        String message = "Another error message";

        Restexception restException = new Restexception(message, null);

        assertEquals(message, restException.getMessage());
        assertEquals(null, restException.getStatus());
    }
}
