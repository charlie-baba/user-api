package com.arc.userapi.services;

import com.arc.userapi.entity.User;
import com.arc.userapi.enums.Role;
import com.arc.userapi.enums.Status;
import com.arc.userapi.pojo.request.UserRequest;
import com.arc.userapi.pojo.response.BaseResponse;
import com.arc.userapi.repository.UserRepository;
import com.arc.userapi.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Charles on 28/02/2021
 */
public class UserServiceTest {

    @Mock
    private UserRepository mockRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

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
        int page = 0;
        int size = 15;
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Mr.", "Charles", "Okonkwo", "charles@email.com",
                "+2348065368787", "pswd", Role.User, now, now, true, null, Status.Verified));
        users.add(new User(2L, "Mrs.", "Stacy", "Krimlin", "stacy@email.com",
                "+2348065368989", "pswd", Role.Admin, now, now, true, null, Status.Verified));
        Page<User> pagedResponse = new PageImpl(users);
        doReturn(pagedResponse).when(mockRepository).findAllActiveUsers(PageRequest.of(page, size));

        //Act
        List<User> fetchedList = service.getAllActiveUsers(page, size);

        //Assert
        Assert.assertEquals(2, fetchedList.size());
        Assert.assertNull(fetchedList.get(0).getPassword());
    }

    @Test
    public void saveUserShouldBeSuccessful(){
        //Arrange
        UserRequest request = new UserRequest("Mr", "Charles", "Okonkwo", "charles@email.com",
                "08065368787", "Password", Role.User, false);

        //Act
        BaseResponse response = service.saveUser(request);

        //Assert
        Assert.assertEquals("00", response.getResponseCode());
        verify(mockRepository).save(any(User.class));
        verify(emailService).sendAccountVerificationEmail(any(User.class));
    }

    @Test
    public void saveUserEmailExists() {
        //Arrange
        UserRequest request = new UserRequest("Mr", "Charles", "Okonkwo", "charles@email.com",
                "08065368787", "Password", Role.User, false);
        User user = new User(1L, "Mr", "Charles", "Okonkwo", "charles@email.com", "08065368787",
                "Password", Role.User, null, null, false, null, null);
        doReturn(user).when(mockRepository).findUserByEmail("charles@email.com");

        //Act
        BaseResponse response = service.saveUser(request);

        //Assert
        Assert.assertEquals("33", response.getResponseCode());
        verify(mockRepository, never()).save(any(User.class));
        verify(emailService, never()).sendAccountVerificationEmail(any(User.class));
    }

    @Test
    public void updateUserShouldBeSuccessful(){
        //Arrange
        Long id = 1L;
        UserRequest request = new UserRequest("Mr", "Charles", "Okonkwo", "charles@email.com",
                "08065368787", "Password", Role.User, true);
        User user = new User(id, "Mr", "Charles", "Okonkwo", "charles@email.com","08065368787",
                "Password", Role.User, null, null, false, null, null);
        doReturn(user).when(mockRepository).findUserById(id);

        //Act
        BaseResponse response = service.updateUser(id, request);

        //Assert
        Assert.assertEquals("00", response.getResponseCode());
        verify(mockRepository).save(any(User.class));
    }

    @Test
    public void updateUserEmailExists() {
        //Arrange
        Long id = 1L;
        String email = "charles@email.com";
        UserRequest request = new UserRequest("Mr", "Ken", "Miles", email, "08065368787",
                "Password", Role.User, false);
        User user1 = new User(id, "Mr", "Ken", "Miles", "user@email.com", "08065368787",
                "Password", Role.User, null, null, false, null, null);
        User user2 = new User(2L, "Mr", "Charles", "Okonkwo", email, "08065368787",
                "Password", Role.User, null, null, false, null, null);
        doReturn(user1).when(mockRepository).findUserById(id);
        doReturn(user2).when(mockRepository).findUserByEmail(email);

        //Act
        BaseResponse response = service.updateUser(id, request);

        //Assert
        Assert.assertEquals("33", response.getResponseCode());
        verify(mockRepository, never()).save(any(User.class));
    }

    @Test
    public void updateUserNotFound() {
        //Arrange
        Long id = 1L;
        UserRequest request = new UserRequest("Mr", "Ken", "Miles", "charles@email.com", "08065368787",
                "Password", Role.User, false);
        doReturn(null).when(mockRepository).findUserById(id);

        //Act
        BaseResponse response = service.updateUser(id, request);

        //Assert
        Assert.assertEquals("22", response.getResponseCode());
        verify(mockRepository, never()).save(any(User.class));
    }

    @Test
    public void verifyUserShouldBeSuccessful(){
        //Arrange
        String code = "123";
        User user = new User(1L, "Mr.", "Charles", "Okonkwo", "charles@email.com",
                "+2348065368787", "", Role.User, now, now, false, null, Status.Verified);
        doReturn(user).when(mockRepository).findUserByVerificationCode(code);

        //Act
        BaseResponse response = service.verifyUser(code);

        //Assert
        Assert.assertEquals(response.getResponseCode(), "00");
        verify(mockRepository).save(any(User.class));
        verify(emailService).sendVerificationConfirmationEmail(any(User.class));
    }

    @Test
    public void verifyUserNotFound() {
        //Arrange
        String code = "123";
        doReturn(null).when(mockRepository).findUserByVerificationCode(code);

        //Act
        BaseResponse response = service.verifyUser(code);

        //Assert
        Assert.assertEquals("22", response.getResponseCode());
        verify(mockRepository, never()).save(any(User.class));
        verify(emailService, never()).sendVerificationConfirmationEmail(any(User.class));
    }

    @Test
    public void deactivateUserShouldBeSuccessful(){
        //Arrange
        Long id = 1L;
        User user = new User(id, "Mr.", "Charles", "Okonkwo", "charles@email.com",
                "+2348065368787", "", Role.User, now, now, true, null, Status.Verified);
        doReturn(user).when(mockRepository).findUserById(id);

        //Act
        BaseResponse response = service.deactivateUser(id);

        //Assert
        Assert.assertEquals(response.getResponseCode(), "00");
        verify(mockRepository).save(any(User.class));
        verify(emailService).sendDeactivationEmail(any(User.class));
    }

    @Test
    public void deactivateUserNotFound() {
        //Arrange
        Long id = 1L;
        doReturn(null).when(mockRepository).findUserById(id);

        //Act
        BaseResponse response = service.deactivateUser(id);

        //Assert
        Assert.assertEquals("22", response.getResponseCode());
        verify(mockRepository, never()).save(any(User.class));
        verify(emailService, never()).sendDeactivationEmail(any(User.class));
    }
}
