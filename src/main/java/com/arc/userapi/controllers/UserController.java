package com.arc.userapi.controllers;

import com.arc.userapi.Enums.ResponseCode;
import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<BaseResponse> saveUser(@RequestBody UserRequest userRequest) {
        try {
            BaseResponse response = userService.saveUser(userRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
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
