package com.arc.userapi.services;

import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;

import java.util.List;

/**
 * @author Charles on 27/02/2021
 */
public interface UserService {

    User findUserById(Long id);

    List<User> getAllActiveUsers(int page, int size);

    BaseResponse saveUser(UserRequest userRequest);

    BaseResponse updateUser(Long id, UserRequest userRequest);

    BaseResponse verifyUser(String code);

    BaseResponse deactivateUser(Long id);
}
