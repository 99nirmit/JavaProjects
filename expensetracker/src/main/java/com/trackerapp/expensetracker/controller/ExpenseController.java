package com.trackerapp.expensetracker.controller;

import com.trackerapp.expensetracker.models.Expense;
import com.trackerapp.expensetracker.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/{userId}/save")
    public Expense save(@PathVariable Long userId, @RequestBody Expense expense){
        return expenseService.save(userId, expense);
    }

    @GetMapping("/{userId}")
    public List<Expense> getAllExpenses(@PathVariable Long userId){
        return expenseService.getALlExpenses(userId);
    }

    @GetMapping("/{id}/{userId}")
    public Expense getExpenses(@PathVariable Long id, @PathVariable Long userId){
        return expenseService.getExpense(id, userId);
    }

}
