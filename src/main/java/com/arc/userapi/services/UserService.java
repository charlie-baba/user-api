package com.arc.userapi.services;

import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.pojo.response.UserResponse;

import java.util.List;

/**
 * @author Charles on 27/02/2021
 */
public interface UserService {

    User findUserById(Long id);

    List<User> getAllActiveUsers();

    UserResponse saveUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    UserResponse deactivateUser(Long id);
}
