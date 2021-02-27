package com.arc.userapi.services.impl;

import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;
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
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean saveUser(UserRequest userRequest) {
        return false;
    }

    @Override
    public boolean updateUser(Long id, UserRequest userRequest) {
        return false;
    }

    @Override
    public boolean deactivateUser(Long id) {
        return false;
    }

}
