package com.arc.userapi.services.impl;

import com.arc.userapi.Enums.Status;
import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.repository.UserRepository;
import com.arc.userapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Charles on 27/02/2021
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllActiveUsers() {
        return repository.findUsersNotInStatus(Status.Deactivated);
    }

    @Override
    public BaseResponse saveUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public BaseResponse updateUser(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public BaseResponse deactivateUser(Long id) {
        return null;
    }
}
