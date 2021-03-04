package com.arc.userapi.Enums;

import lombok.Getter;

/**
 * @author Charles on 02/03/2021
 */
@Getter
public enum ResponseCode {

    Success("00", "Successful"),
    Not_Found("22", "Requested Resource Not Found"),
    Bad_Request("33", "Bad/Invalid Request"),
    Internal_Server_Error("ZZ", "Something went wrong. Please try again later."),
    ;

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
