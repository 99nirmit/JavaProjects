package com.trackerapp.expensetracker.services;

import com.trackerapp.expensetracker.datahelper.UserDataHelper;
import com.trackerapp.expensetracker.models.User;
import com.trackerapp.expensetracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void saveUser() {

        //Arrange

        //Input Prepare
        User newUserCreate = UserDataHelper.createNewUser();


        //Expected O/P
        String encodePassword = "hashedPassword";
        User expectedUser = UserDataHelper.createSavedUser();


        //Mock behavior
        when(passwordEncoder.encode(newUserCreate.getPassword()))
                .thenReturn(encodePassword);

        when(userRepository.save(newUserCreate))
                .thenReturn(expectedUser);


        //Act
        //Actual function call

        User result = userService.saveUser(newUserCreate);

        //Assert
        //Result Verify

        assertEquals(expectedUser, result);
        assertEquals(expectedUser.getId(), result.getId());
        assertEquals(expectedUser.getName(), result.getName());
        assertEquals(expectedUser.getEmail(), result.getEmail());
        assertEquals(expectedUser.getPassword(), result.getPassword());
        assertEquals(expectedUser.getMobile(), result.getMobile());
        assertEquals(expectedUser.getExpenses(), result.getExpenses());
        assertEquals(expectedUser.getRole(), result.getRole());

        verify(passwordEncoder).encode(anyString());
        verify(userRepository).save(any(User.class));
    }


    @Test
    void getAllUser() {

        //Arrange
        //No Inputs

        //Expected o/p create
        List<User> expectedUserList = UserDataHelper.createUserLists(5);

        //Mock Behaviour
        when(userRepository.findAll())
                .thenReturn(expectedUserList);

        //Act
        //Actual function call
        List<User> result = userService.getAllUser();

        //Assert
        //Result Verify

        assertEquals(5, result.size());

        verify(userRepository).findAll();
    }

    @Test
    void getUser() {

        //Arrange

        //I/P
        Long id = 1L;

        //Expected O/P create
        User expectedUser = UserDataHelper.createSavedUser();

        //Mock Behaviour
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(expectedUser));

        //Act
        //Actual Function Call
        User result = userService.getUser(1L);

        //Assert
        //Result Verify

        assertEquals(expectedUser, result);
        assertEquals(expectedUser.getId(), result.getId());

        verify(userRepository).findById(1L);
    }
}