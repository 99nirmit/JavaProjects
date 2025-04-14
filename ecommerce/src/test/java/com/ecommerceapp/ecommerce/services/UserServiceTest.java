package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.Enum.Role;
import com.ecommerceapp.ecommerce.Enum.Status;
import com.ecommerceapp.ecommerce.dataHelper.UserDataHelper;
import com.ecommerceapp.ecommerce.models.Cart;
import com.ecommerceapp.ecommerce.models.User;
import com.ecommerceapp.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void saveUser() {
        //Arrange
        //I/P Prepare
        User createUser = UserDataHelper.createNewUsers();

        //Expected Result
        User expectedUser = UserDataHelper.createSavedUsers();

        //Mock Behaviour
        when(userRepository.save(createUser))
                .thenReturn(expectedUser);

        //Act
        //Actual function call
        User result = userService.saveUser(createUser);

        //Assert

        assertEquals(expectedUser, result);
        assertEquals(expectedUser.getId(), result.getId());

        verify(userRepository).save(any(User.class));

    }

    @Test
    void getAllUser() {
        //Arrange
        //No I/P
        //Expected O/P
        List<User> expected = UserDataHelper.createUsersList(5);

        //Mock Behaviour
        when(userRepository.findAll())
                .thenReturn(expected);

        //Act
        List<User> result = userService.getAllUser();

        //Assert
        //Verify Result

        assertEquals(expected, result);
        assertEquals(5, result.size());
    }

    @Test
    void getUser() {

        //Arrange
        //I/P
        Long userId = 1L;

        //Expected Result
        User expected = UserDataHelper.createSavedUsers();

        //Mock Behaviour
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(expected));

        //Act
        User result = userService.getUser(userId);

        //Assert
        //Verify

        assertEquals(expected, result);
        assertEquals(expected.getId(), result.getId());

        verify(userRepository).findById(userId);
    }

    @Test
    void updateUser() {
        //Arrange

        //I/P Prepare
        Long userId = 1L;
        User existingUser = UserDataHelper.createUser(1L, "Nirmit", "nirmit@gmail.com", "1234", "987654321", Role.USER, "Add", Status.ACTIVE, new Cart(), new ArrayList<>());
        User newUser = UserDataHelper.createUser(1L, "Uday", "uday@gmail.com", "1234", "9812654321", Role.USER, "Addd", Status.ACTIVE, new Cart(), new ArrayList<>());

        //Mock
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(existingUser));

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //Act
        //Actual Function call
        User result = userService.updateUser(userId, newUser);

        //Assert
        //verify result

        assertEquals(1L, result.getId());
        assertEquals("Uday", result.getName());
        assertEquals("uday@gmail.com", result.getEmail());

        verify(userRepository).findById(userId);
        verify(userRepository).save(existingUser);

    }

    @Test
    void deleteUser() {

        //Arrange
        //Mock
        doNothing().when(userRepository).deleteById(1L);

        //Act
        userService.deleteUser(1L);

        //Assert
        verify(userRepository).deleteById(1L);


    }
}