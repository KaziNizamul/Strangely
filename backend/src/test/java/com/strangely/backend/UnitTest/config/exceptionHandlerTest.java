package com.strangely.backend.UnitTest.config;

import com.strangely.backend.Model.Exception.exceptionDTO;
import com.strangely.backend.Service.Exception.Restexception;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.strangely.backend.Config.Exception.exceptionHandler;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class exceptionHandlerTest {

    private exceptionHandler exceptionHandler;

    @Before
    public void setUp() {
        exceptionHandler = new exceptionHandler();
    }

    @Test
    public void testHandleException() {
        // Arrange
        Restexception restException = new Restexception("Test Exception", HttpStatus.BAD_REQUEST);

        // Act
        ResponseEntity<exceptionDTO> responseEntity = exceptionHandler.handleException(restException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Test Exception", responseEntity.getBody().getMessage());
    }

    @Test
    public void testHandleExceptionWithDifferentHttpStatus() {
        // Arrange
        Restexception restException = new Restexception("Another Exception", HttpStatus.INTERNAL_SERVER_ERROR);

        // Act
        ResponseEntity<exceptionDTO> responseEntity = exceptionHandler.handleException(restException);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Another Exception", responseEntity.getBody().getMessage());
    }

}

