package com.arc.userapi.utils;

import com.arc.userapi.enums.ResponseCode;
import com.arc.userapi.pojo.response.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Charles on 04/03/2021
 */
@Component
public class EmailUtil {

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Value("${email.host}")
    private String host;

    @Value("${email.port}")
    private int port;

    public BaseResponse sendMail(String toEmail, String subject, String body){
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };
            Session session = Session.getDefaultInstance(props, auth);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);

            return new BaseResponse(ResponseCode.Success);
        } catch (Exception e) {
            return new BaseResponse(ResponseCode.Internal_Server_Error.getCode(), e.getMessage());
        }
    }
}
