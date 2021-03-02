package com.arc.userapi.services.impl;

import com.arc.userapi.Enums.ResponseCode;
import com.arc.userapi.Enums.Status;
import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.repository.UserRepository;
import com.arc.userapi.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Charles on 27/02/2021
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

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
        User user = new User();
        BeanUtils.copyProperties(user, userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setDateRegistered(new Date());
        user.setStatus(Status.Registered);
        repository.save(user);
        return new BaseResponse(ResponseCode.Success);
    }

    @Override
    public BaseResponse updateUser(Long id, UserRequest userRequest) {
        User user = findUserById(id);
        if (user == null){
            return new BaseResponse(ResponseCode.Not_Found);
        }

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setMobile(userRequest.getMobile());
        user.setRole(userRequest.getRole());
        if (userRequest.getPassword() != null && !userRequest.getPassword().isBlank())
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        if (!user.isVerified() && userRequest.isVerified()){
            user.setVerified(userRequest.isVerified());
            user.setDateVerified(new Date());
            user.setStatus(Status.Verified);
        }
        repository.save(user);
        return new BaseResponse(ResponseCode.Success);
    }

    @Override
    public BaseResponse deactivateUser(Long id) {
        User user = findUserById(id);
        if (user == null){
            return new BaseResponse(ResponseCode.Not_Found);
        }

        user.setStatus(Status.Deactivated);
        user.setDateDeactivated(new Date());
        repository.save(user);
        return new BaseResponse(ResponseCode.Success);
    }
}
