package com.trackerapp.expensetracker.controller;

import com.trackerapp.expensetracker.models.Expense;
import com.trackerapp.expensetracker.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

//    @GetMapping("/page")
//    public Page<Expense> getExpenseByFilters(@PathVariable Long userId,
//                                             @RequestParam(required = false) Double minAmount,
//                                             @RequestParam(required = false) Double maxAmount,
//                                             @RequestParam(required = false) LocalDate startDate,
//                                             @RequestParam(required = false) LocalDate endDate,
//                                             @RequestParam(defaultValue = "0") int page,
//                                             @RequestParam(defaultValue = "10") int size,
//                                             @RequestParam(defaultValue = "createdAt") String sortBy,
//                                             @RequestParam(defaultValue = "desc") String order
//                                            ){
//        return expenseService.getExpenseByFilters(userId, minAmount, maxAmount, startDate, endDate, page, size, sortBy, order);
//    }

    @PutMapping("/{userId}/{id}/update")
    public Expense updateExpenses(@PathVariable Long userId, @PathVariable Long id, @RequestBody Expense expense){
        return expenseService.updateExpense(userId, id, expense);
    }

}
