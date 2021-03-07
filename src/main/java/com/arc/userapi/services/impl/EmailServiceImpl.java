package com.arc.userapi.services.impl;

import com.arc.userapi.entity.User;
import com.arc.userapi.enums.ResponseCode;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.services.EmailService;
import com.arc.userapi.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.net.InetAddress;

/**
 * @author Charles on 04/03/2021
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private ServletContext context;

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    private final String verifyUrl = "/api/verify/";

    @Override
    public BaseResponse sendAccountVerificationEmail(User user) {
        String subject = "New Account Activation";
        try {
            String host =  InetAddress.getLocalHost().getCanonicalHostName();
            int port = webServerAppCtxt.getWebServer().getPort();
            host += (port != 0) ? ":"+ port : "";

            String url = host + verifyUrl + user.getVerificationCode();
            String body = "Hi " + user.getFirstName() + ", \n Kindly use the link below to verify your account. \n" +
                    url + "\n Cheers!";
            return emailUtil.sendMail(user.getEmail(), subject, body);
        } catch (Exception e){
            return new BaseResponse(ResponseCode.Internal_Server_Error);
        }
    }

    @Override
    public BaseResponse sendVerificationConfirmationEmail(User user) {
        String subject = "Account Verified";
        try {
            String body = "Hi " + user.getFirstName() +
                    ", \n Your account has been verified! \n" +
                    "\n Cheers!";
            return emailUtil.sendMail(user.getEmail(), subject, body);
        } catch (Exception e){
            return new BaseResponse(ResponseCode.Internal_Server_Error);
        }
    }

    @Override
    public BaseResponse sendDeactivationEmail(User user) {
        String subject = "Account Deactivation";
        try {
            String body = "Hi " + user.getFirstName() +
                    ", \n Your account has been deactivated. \n" +
                    "\n Regards.";
            return emailUtil.sendMail(user.getEmail(), subject, body);
        } catch (Exception e){
            return new BaseResponse(ResponseCode.Internal_Server_Error);
        }
    }
}
