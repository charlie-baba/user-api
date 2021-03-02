package com.arc.userapi.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * @author Charles on 02/03/2021
 */
@Component
public class ErrorUtil {

    public static String getResponseMessage(Errors errors) {
        StringBuilder sb = new StringBuilder();
        errors.getAllErrors().forEach((error) -> {
            sb.append(error.getDefaultMessage()).append(", ");
        });
        return sb.toString();
    }
}
