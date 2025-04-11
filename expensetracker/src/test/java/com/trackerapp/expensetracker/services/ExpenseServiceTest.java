package com.trackerapp.expensetracker.services;

import com.trackerapp.expensetracker.datahelper.ExpenseDataHelper;
import com.trackerapp.expensetracker.models.Expense;
import com.trackerapp.expensetracker.models.User;
import com.trackerapp.expensetracker.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void save() {

        //Arrange

        //Input prepare
        Long userId = 1L;
        Expense newExpense = ExpenseDataHelper.createNewExpense();

        //Expected O/P
        Expense expectedExpense = ExpenseDataHelper.createSavedExpense();

        when(expenseRepository.save(newExpense))
                .thenReturn(expectedExpense);

        //Act

        //Mock behavior
        Expense result = expenseService.save(userId, newExpense);

        //Assert

        //Result verify kiya
        assertEquals(expectedExpense, result);
        assertEquals(expectedExpense.getId(), result.getId());
        assertEquals(expectedExpense.getAmount(), result.getAmount());
        assertEquals(expectedExpense.getUser(), result.getUser());
        assertEquals(expectedExpense.getCategory(), result.getCategory());
        assertEquals(expectedExpense.getDescription(), result.getDescription());
        assertEquals(expectedExpense.getDate(), result.getDate());

        verify(expenseRepository).save(ArgumentMatchers.any(Expense.class));
    }

    @Test
    void getALlExpenses() {
    }

    @Test
    void getExpense() {
    }

    @Test
    void updateExpense() {
    }

    @Test
    void deleteExpense() {
    }
}