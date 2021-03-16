package com.arc.userapi.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;

/**
 * @author Charles on 02/03/2021
 */
@Component
public class ErrorUtil {

    public static String getResponseMessage(Errors errors) {
        if (errors == null || CollectionUtils.isEmpty(errors.getAllErrors()))
            return null;

        StringBuilder sb = new StringBuilder();
        errors.getAllErrors().forEach((error) -> {
            sb.append(error.getDefaultMessage()).append(", ");
        });
        return sb.toString();
    }
}
