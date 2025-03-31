package com.trackerapp.expensetracker.services;

import com.trackerapp.expensetracker.models.Expense;
import com.trackerapp.expensetracker.models.User;
import com.trackerapp.expensetracker.repository.ExpenseRepository;
import com.trackerapp.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public Expense save(Long userId, Expense expense){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not Found with" + userId));

        expense.setUser(user);
        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());

        return expenseRepository.save(expense);
    }

    public List<Expense> getALlExpenses(Long userId){
        User user = userRepository.findById(userId).get();
        return user.getExpenses();

    }

    public Expense getExpense(Long id, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with " + userId));

            return expenseRepository.findUserByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Expense Not Found By user_id " + userId + " and id " + id));
    }

    // update & pagination
    // Filter Expenses (By Category, Date Range, or Amount)
    // Generate Expense Summary (Total Expenses in a given period)


}
