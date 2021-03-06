package com.arc.userapi.services.impl;

import com.arc.userapi.entity.User;
import com.arc.userapi.enums.ResponseCode;
import com.arc.userapi.enums.Status;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.repository.UserRepository;
import com.arc.userapi.services.EmailService;
import com.arc.userapi.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Charles on 27/02/2021
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long id) {
        return repository.findUserById(id);
    }

    @Override
    public List<User> getAllActiveUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = repository.findAllActiveUsers(pageable);
        userPage.forEach(x -> x.setPassword(null));
        List<User> users = userPage.getContent();
        return users;
    }

    @Override
    public BaseResponse saveUser(UserRequest userRequest) {
        if (repository.findUserByEmail(userRequest.getEmail()) != null) {
            return new BaseResponse(ResponseCode.Bad_Request.getCode(), "A user with this email already exists.");
        }

        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setDateRegistered(new Date());
        user.setStatus(Status.Registered);
        user.setVerificationCode(UUID.randomUUID().toString());
        repository.save(user);
        emailService.sendAccountVerificationEmail(user);
        return new BaseResponse(ResponseCode.Success);
    }

    @Override
    public BaseResponse updateUser(Long id, UserRequest userRequest) {
        User user = findUserById(id);
        if (user == null) {
            return new BaseResponse(ResponseCode.Not_Found);
        }

        User emailUser = repository.findUserByEmail(userRequest.getEmail());
        if (emailUser != null && !emailUser.getId().equals(user.getId())) {
            return new BaseResponse(ResponseCode.Bad_Request.getCode(), "A user with this email already exists.");
        }

        user.setTitle(userRequest.getTitle());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setMobile(userRequest.getMobile());
        user.setRole(userRequest.getRole());
        if (StringUtils.isNotBlank( userRequest.getPassword()))
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        repository.save(user);
        return new BaseResponse(ResponseCode.Success);
    }

    @Override
    public BaseResponse verifyUser(String code) {
        User user = repository.findUserByVerificationCode(code);
        if (user == null){
            return new BaseResponse(ResponseCode.Not_Found);
        }

        user.setStatus(Status.Verified);
        user.setDateVerified(new Date());
        user.setVerified(true);
        repository.save(user);
        emailService.sendVerificationConfirmationEmail(user);
        return new BaseResponse(ResponseCode.Success);
    }

    @Override
    public BaseResponse deactivateUser(Long id) {
        User user = findUserById(id);
        if (user == null) {
            return new BaseResponse(ResponseCode.Not_Found);
        }

        user.setStatus(Status.Deactivated);
        user.setDateDeactivated(new Date());
        repository.save(user);
        emailService.sendDeactivationEmail(user);
        return new BaseResponse(ResponseCode.Success);
    }
}
