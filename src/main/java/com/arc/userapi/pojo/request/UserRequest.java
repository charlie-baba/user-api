package com.arc.userapi.pojo.request;

import com.arc.userapi.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Charles on 27/02/2021
 */
@Getter
@Setter
@ToString
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest implements Serializable {

    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;
    private Role role;
    private boolean verified = false;

    public UserRequest(String title, String firstName, String lastName, String email, String mobile, String password, Role role, boolean verified) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
        this.verified = verified;
    }
}
