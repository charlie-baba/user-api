package com.arc.userapi.pojo.request;

import com.arc.userapi.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author Charles on 27/02/2021
 */
@Getter
@Setter
@ToString
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    private String title;
    private String lastName;
    private String email;
    private String mobile;
    private String password;
    private Role role;
    private boolean verified = false;
}
