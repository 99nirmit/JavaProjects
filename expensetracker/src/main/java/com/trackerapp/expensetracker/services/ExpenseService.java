package com.trackerapp.expensetracker.services;

import com.trackerapp.expensetracker.models.Expense;
import com.trackerapp.expensetracker.models.User;
import com.trackerapp.expensetracker.repository.ExpenseRepository;
import com.trackerapp.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public Expense save(Expense expense){
        return expenseRepository.save(expense);
    }

    public List<Expense> getALlExpenses(Long userId){
        User user = userRepository.findById(userId).get();
        return user.getExpenses();

    }

    public Expense getExpense(Long userId, Long id){
        User user = userRepository.findById(userId).get();
        Optional<Expense> ExpenseById = expenseRepository.findById(id);

    }
}
