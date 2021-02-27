package com.arc.userapi.services;

import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;

import java.util.List;

/**
 * @author Charles on 27/02/2021
 */
public interface UserService {

    List<User> getAllUsers();

    boolean saveUser(UserRequest userRequest);

    boolean updateUser(Long id, UserRequest userRequest);

    boolean deactivateUser(Long id);
}
