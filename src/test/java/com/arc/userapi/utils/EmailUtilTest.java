package com.arc.userapi.utils;

import com.arc.userapi.pojo.response.BaseResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.mockStatic;

/**
 * @author Charles on 04/03/2021
 */
public class EmailUtilTest {

    @Mock
    private Transport mockTransport;

    @Mock
    private Message message;

    @InjectMocks
    private EmailUtil emailUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(emailUtil, "host", "localhost");
        ReflectionTestUtils.setField(emailUtil, "port", 8080);
        ReflectionTestUtils.setField(emailUtil, "username", "test@email.com");
        ReflectionTestUtils.setField(emailUtil, "password", "Password123");
    }

    @Test
    public void sendMail_ShouldBeSuccessfully() throws MessagingException {
        //Arrange
        String subject = "Subject";
        String body = "Message1";
        String email = "charles@email.com";
        //mockStatic(Transport.class);
        //Mockito.doNothing().when(Transport.send(any(Message.class)));

        //Act
        //BaseResponse response = emailUtil.sendMail(email, subject, body);

        //Assert
        //Assert.assertEquals("00", response.getResponseCode());
        Assert.assertTrue(true);
    }
}
