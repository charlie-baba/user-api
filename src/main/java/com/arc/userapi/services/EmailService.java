package com.arc.userapi.services;

import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.response.BaseResponse;

/**
 * @author Charles on 04/03/2021
 */
public interface EmailService {

    BaseResponse sendAccountVerificationEmail(User user);

    BaseResponse sendVerificationConfirmationEmail(User user);

    BaseResponse sendDeactivationEmail(User user);
}
