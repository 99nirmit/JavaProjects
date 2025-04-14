package com.trackerapp.expensetracker.services;

import com.trackerapp.expensetracker.datahelper.ExpenseDataHelper;
import com.trackerapp.expensetracker.datahelper.UserDataHelper;
import com.trackerapp.expensetracker.models.Expense;
import com.trackerapp.expensetracker.models.User;
import com.trackerapp.expensetracker.repository.ExpenseRepository;
import com.trackerapp.expensetracker.repository.UserRepository;
import org.apache.catalina.UserDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @InjectMocks
    private UserService userService;

    @Test
    void save() {
        //Arrange

        // I/P Prepare
        Long userId = 1L;
        Expense newExpense = ExpenseDataHelper.createNewExpense();

        //Expected o/p
        User expectedUser = UserDataHelper.createSavedUser();
        Expense expectedExpanses = ExpenseDataHelper.createSavedExpense();

        //Mock Behaviour
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(expectedUser));

        when(expenseRepository.save(newExpense))
                .thenReturn(expectedExpanses);

        //Act
        //Actual function call

        Expense resultExpense = expenseService.save(userId, newExpense);

        //Assert
        //Result Verify

        assertEquals(expectedExpanses, resultExpense);
        assertEquals(expectedExpanses.getId(), resultExpense.getId());
        assertEquals(expectedExpanses.getUser(), resultExpense.getUser());

        verify(userRepository).findById(userId);
        verify(expenseRepository).save(any(Expense.class));

    }

    @Test
    void getALlExpenses() {
        //Arrange
        //I/P Prepare

        Long userId = 1L;

        //Expected Result
        User expectedUser = UserDataHelper.createSavedUser();
        List<Expense> expectedExpenses = ExpenseDataHelper.createExpenseList(5);
        
        for(Expense expense : expectedExpenses){
            expense.setUser(expectedUser);
        }

        expectedUser.setExpenses(expectedExpenses);

        //Mock Behavior
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(expectedUser));

        //Act
        //Actual Function Call

        List<Expense> resultExpensesList = expenseService.getALlExpenses(userId);

        //Assert
        //Result Verify

        assertEquals(expectedExpenses, resultExpensesList);
        assertEquals(5, resultExpensesList.size());

        verify(userRepository).findById(userId);
    }

    @Test
    void getExpense() {

        //Arrange

        //I/P Prepare
        Long id = 1L;
        Long userId = 1L;

        //Expected Result
        User expectedUser = UserDataHelper.createSavedUser();
        Expense expectedExpense = ExpenseDataHelper.createSavedExpense();

        expectedExpense.setUser(expectedUser);

        when(expenseRepository.findUserByIdAndUserId(id, userId))
                .thenReturn(Optional.of(expectedExpense));
        //Act
        //Actual Function Call

        Expense result = expenseService.getExpense(id, userId);

        //Assert
        //Result verify

        assertEquals(expectedExpense, result);
        assertEquals(expectedExpense.getUser(), result.getUser());
        assertEquals(expectedExpense.getId(), result.getId());

        verify(expenseRepository).findUserByIdAndUserId(id, userId);
    }

    @Test
    void updateExpense() {

        //Arrange
        //I/P Prepare
        Long userId = 1L;
        Long id = 1L;

        Expense existedExpense = ExpenseDataHelper.createExpense(1L, 8982.1, new User(), "Foods", "Foods Junk", new Date());
        Expense updateExpense = ExpenseDataHelper.createExpense(1L, 81282.1, new User(), "Vehicle", "Fuel", new Date());

        //Mock Behaviour
        when(expenseRepository.findUserByIdAndUserId(1L, 1L))
                .thenReturn(Optional.of(existedExpense));

        when(expenseRepository.save(any(Expense.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //Act
        //Actual Function Call

        Expense result = expenseService.updateExpense(userId, id, updateExpense);

        //Assert
        //Verify

        assertEquals(1L, result.getId());
        assertEquals(81282.1, result.getAmount());

        verify(expenseRepository).findUserByIdAndUserId(id, userId);
        verify(expenseRepository).save(existedExpense);

    }

    @Test
    void deleteExpense() {

        //Arrange
        //I/P Prepare

        Long userId = 1L;
        Long id = 1L;

        //Mock Behaviour
        doNothing().when(expenseRepository).deleteUserByIdAndUserId(id, userId);

        //Act
        expenseService.deleteExpense(userId, id);

        //Assert
        verify(expenseRepository).deleteUserByIdAndUserId(id, userId);


        //Act



    }
}