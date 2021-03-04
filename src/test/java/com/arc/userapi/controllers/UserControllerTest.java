package com.arc.userapi.controllers;

import com.arc.userapi.Enums.ResponseCode;
import com.arc.userapi.Enums.Role;
import com.arc.userapi.Enums.Status;
import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Charles on 03/03/2021
 */
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Mock
    UserService mockService;

    @InjectMocks
    UserController userControllerTest;

    private MockMvc mockMvc;
    private final Date now = new Date();
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userControllerTest).build();
    }

    @Test
    public void getUsers_ShouldBeSuccessful() throws Exception {
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Mr.", "Charles", "Okonkwo", "charles@email.com", "+2348065368787", "",
                Role.User, now, now, true, null, Status.Verified));
        users.add(new User(2L, "Mrs.", "Stacy", "Krimlin", "stacy@email.com", "+2348065368989", "",
                Role.Admin, now, now, true, null, Status.Verified));
        doReturn(users).when(mockService).getAllActiveUsers();

        //Act //Assert
        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    public void saveUser_ShouldBeSuccessful() throws Exception {
        //Arrange
        UserRequest request = new UserRequest("Mr", "Charles", "Okonkwo", "charles@email.com",
                "08065368787", "Password", Role.User, false);
        BaseResponse successResponse = new BaseResponse(ResponseCode.Success);
        doReturn(successResponse).when(mockService).saveUser(request);

        //Act //Assert
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void saveUser_withErrors_ShouldReturnBadRequest() throws Exception {
        //Arrange
        UserRequest request = new UserRequest("Mr", "Charles", "", "charles@",
                "08065368787", "Password", Role.User, false);
        BaseResponse successResponse = new BaseResponse(ResponseCode.Success);
        doReturn(successResponse).when(mockService).saveUser(request);

        //Act //Assert
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUser_ShouldBeSuccessful() throws Exception {
        //Arrange
        Long id = 1L;
        UserRequest request = new UserRequest("Mr", "Charles", "Okonkwo", "charles@email.com",
                "08065368787", "Password", Role.User, false);
        BaseResponse successResponse = new BaseResponse(ResponseCode.Success);
        doReturn(successResponse).when(mockService).updateUser(id, request);

        //Act //Assert
        this.mockMvc.perform(put("/api/user/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser_shouldBeSuccessful() throws Exception {
        //Arrange
        Long id = 1L;
        BaseResponse successResponse = new BaseResponse(ResponseCode.Success);
        doReturn(successResponse).when(mockService).deactivateUser(id);

        //Act //Assert
        this.mockMvc.perform(delete("/api/user/{id}", id))
                .andExpect(status().isOk());
    }
}