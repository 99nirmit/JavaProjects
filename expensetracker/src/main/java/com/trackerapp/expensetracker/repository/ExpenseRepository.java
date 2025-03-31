package com.trackerapp.expensetracker.repository;

import com.trackerapp.expensetracker.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findUserByIdAndUserId(Long id, Long userId);
}
