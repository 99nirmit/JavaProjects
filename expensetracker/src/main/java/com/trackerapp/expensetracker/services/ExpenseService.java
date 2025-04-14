package com.trackerapp.expensetracker.services;

import com.trackerapp.expensetracker.models.Expense;
import com.trackerapp.expensetracker.models.User;
import com.trackerapp.expensetracker.repository.ExpenseRepository;
import com.trackerapp.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            return expenseRepository.findUserByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Expense Not Found By user_id " + userId + " and id " + id));
    }

    public Expense updateExpense(Long userId, Long id, Expense updateExpense){

        Expense existingExpense = expenseRepository.findUserByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Expense Not Found By id" + id));

        existingExpense.setAmount(updateExpense.getAmount());
        existingExpense.setCategory(updateExpense.getCategory());
        existingExpense.setDescription(updateExpense.getDescription());
        existingExpense.setDate(updateExpense.getDate());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long userId, Long id){
        expenseRepository.deleteUserByIdAndUserId(id, userId);
    }

//    public Page<Expense> getExpenseByFilters(Long userId, Double minAmount, Double endDate,
//                                             LocalDate startDate, LocalDate endDate, int page,
//                                             int size, String sortBy, String order){
//        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        if(minAmount != null && endDate != null){
//            return expenseRepository.findByUserIdAndAmountBetween(userId, minAmount, endDate, pageable);
//        } else if (startDate != null && endDate != null) {
//            return expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate, pageable);
//        }else{
//            return expenseRepository.findByUserId(userId, pageable);
//        }
//    }


    // update & pagination
    // Filter Expenses (By Category, Date Range, or Amount)
    // Generate Expense Summary (Total Expenses in a given period)




}
