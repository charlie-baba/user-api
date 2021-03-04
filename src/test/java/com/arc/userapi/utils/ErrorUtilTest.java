package com.arc.userapi.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

/**
 * @author Charles on 04/03/2021
 */
public class ErrorUtilTest {

    @Mock
    private Errors mockErrors;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getResponseMessage_ShouldReturnErrors(){
        //Arrange
        List<ObjectError> errors = new ArrayList<>();
        errors.add(new ObjectError("firstName", "First name is required"));
        doReturn(errors).when(mockErrors).getAllErrors();

        //Act
        String message = ErrorUtil.getResponseMessage(mockErrors);

        //Assert
        Assert.assertEquals("First name is required, ", message);
    }
}
