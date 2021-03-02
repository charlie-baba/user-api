package com.arc.userapi.controllers;

import com.arc.userapi.Enums.ResponseCode;
import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.services.UserService;
import com.arc.userapi.utils.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Charles on 27/02/2021
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseBody
    public List<User> getUsers(){
        return userService.getAllActiveUsers();
    }

    @PostMapping("/user")
    public ResponseEntity<BaseResponse> saveUser(@Valid @RequestBody UserRequest userRequest,
                                                 Errors errors) {
        BaseResponse response;
        if(errors.hasErrors()) {
            response = new BaseResponse(ResponseCode.Bad_Request);
            response.setResponseMessage(ErrorUtil.getResponseMessage(errors));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            response = userService.saveUser(userRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error while saving user"+ e.getMessage());
            return new ResponseEntity<>(new BaseResponse(ResponseCode.Internal_Server_Error), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody UserRequest userRequest,
                                     @PathVariable("id") Long id){
        try {
            BaseResponse response = userService.updateUser(id, userRequest);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Error while updating user"+ e.getMessage());
            return new ResponseEntity<>(new BaseResponse(ResponseCode.Internal_Server_Error), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable("id") Long id){
        try {
            BaseResponse response = userService.deactivateUser(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse(ResponseCode.Internal_Server_Error), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
