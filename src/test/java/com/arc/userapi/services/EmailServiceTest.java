package com.arc.userapi.services;

import com.arc.userapi.entity.User;
import com.arc.userapi.enums.ResponseCode;
import com.arc.userapi.enums.Role;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.services.impl.EmailServiceImpl;
import com.arc.userapi.utils.EmailUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

/**
 * @author Charles on 04/03/2021
 */
public class EmailServiceTest {

    @Mock
    private EmailUtil mockEmailUtil;

    @Mock
    private ServletWebServerApplicationContext mockContext;

    @InjectMocks
    private EmailServiceImpl service;

    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "Mr", "Charles", "Okonkwo", "charles@email.com", "08065368787",
                "Password", Role.User, null, null, false, null, null);
        doReturn(new BaseResponse(ResponseCode.Success)).when(mockEmailUtil).sendMail(anyString(), anyString(), anyString());
    }

    @Test
    public void sendAccountVerificationEmail_ShouldBeSuccessful(){
        //Arrange
        doReturn(new WebServer() {
            @Override
            public void start() throws WebServerException { }

            @Override
            public void stop() throws WebServerException { }

            @Override
            public int getPort() {
                return 8080;
            }
        }).when(mockContext).getWebServer();

        //Act
        BaseResponse response = service.sendAccountVerificationEmail(user);

        //Assert
        Assert.assertEquals("00", response.getResponseCode());
    }

    @Test
    public void sendVerificationConfirmationEmail_ShouldBeSuccessful(){
        //Act
        BaseResponse response = service.sendVerificationConfirmationEmail(user);

        //Assert
        Assert.assertEquals("00", response.getResponseCode());
    }

    @Test
    public void sendDeactivationEmail_ShouldBeSuccessful(){
        //Act
        BaseResponse response = service.sendDeactivationEmail(user);

        //Assert
        Assert.assertEquals("00", response.getResponseCode());
    }
}
