package com.trackerapp.expensetracker.repository;

import com.trackerapp.expensetracker.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
