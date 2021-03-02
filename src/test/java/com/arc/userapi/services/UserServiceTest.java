package com.arc.userapi.services;

import com.arc.userapi.Enums.Role;
import com.arc.userapi.Enums.Status;
import com.arc.userapi.entity.User;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.repository.UserRepository;
import com.arc.userapi.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doReturn;

/**
 * @author Charles on 28/02/2021
 */
public class UserServiceTest {

    @Mock
    private UserRepository mockRepository;

    @InjectMocks
    private UserServiceImpl service;

    private final Date now = new Date();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllActiveUsers(){
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Mr.", "Charles", "Okonkwo", "charles@email.com", "+2348065368787", "",
                Role.User, now, now, true, null, Status.Verified));
        users.add(new User(2L, "Mrs.", "Stacy", "Krimlin", "stacy@email.com", "+2348065368989", "",
                Role.Admin, now, now, true, null, Status.Verified));
        doReturn(users).when(mockRepository).findUsersNotInStatus(Status.Deactivated);

        //Act
        List<User> fetchedList = service.getAllActiveUsers();

        //Assert
        Assert.assertEquals(fetchedList.size(), 2);
    }

    @Test
    public void saveUser_shouldBeSuccessful(){
        //Arrange
        UserRequest request = new UserRequest("Mr", "Charles", "Okonkwo", "charles@email.com",
                "08065368787", "Password", Role.User, false);

        //Act
        BaseResponse response = service.saveUser(request);

        //Assert
        Assertions.assertAll("Should add user to repository",
                () -> Assert.assertEquals(response.getResponseCode(), "00"),
                () -> Assert.assertEquals(mockRepository.findUsersNotInStatus(Status.Deactivated).size(), 1));
    }

    @Test
    public void updateUser_ShouldBeSuccessful(){
        //Arrange
        Long id = 1L;
        UserRequest request = new UserRequest("Mr", "Charles", "Okonkwo", "charles@email.com",
                "08065368787", "Password", Role.User, false);
        service.saveUser(request);
        request.setFirstName("Frank");

        //Act
        BaseResponse response = service.updateUser(id, request);

        //Assert
        Assertions.assertAll("Should update user in repository",
                () -> Assert.assertEquals(response.getResponseCode(), "00"),
                () -> Assert.assertEquals(service.findUserById(id).getFirstName(), "Frank"),
                () -> Assert.assertEquals(mockRepository.findUsersNotInStatus(Status.Deactivated).size(), 1));
    }

    @Test
    public void deactivateUser_ShouldBeSuccessful(){
        //Arrange
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Mr.", "Charles", "Okonkwo", "charles@email.com", "+2348065368787", "",
                Role.User, now, now, true, null, Status.Verified));
        doReturn(users).when(mockRepository).findUsersNotInStatus(Status.Deactivated);

        //Act
        BaseResponse response = service.deactivateUser(1L);

        //Assert
        Assertions.assertAll("Should add user to repository",
                () -> Assert.assertEquals(response.getResponseCode(), "00"),
                () -> Assert.assertEquals(mockRepository.findUsersNotInStatus(Status.Deactivated).size(), 0));
    }
}
