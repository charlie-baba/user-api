package com.arc.userapi.pojo.response;

import com.arc.userapi.pojo.request.UserRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author Charles on 03/03/2021
 */
@Getter
@Setter
@ToString
@Component
public class UserResponse extends BaseResponse {

    private UserRequest userRequest;

    public UserResponse() {}

    public UserResponse(UserRequest userRequest, BaseResponse baseResponse) {
        this.userRequest = userRequest;
        this.setResponseCode(baseResponse.getResponseCode());
        this.setResponseMessage(baseResponse.getResponseMessage());
    }
}
