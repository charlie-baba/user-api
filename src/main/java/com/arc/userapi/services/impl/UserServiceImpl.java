package com.arc.userapi.services.impl;

import com.arc.userapi.repository.UserRepository;
import com.arc.userapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Charles on 27/02/2021
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


}
