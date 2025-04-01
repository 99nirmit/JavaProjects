package com.trackerapp.expensetracker.repository;

import com.trackerapp.expensetracker.models.Expense;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findUserByIdAndUserId(Long id, Long userId);

    Page<Expense> findByUserId(Long userId, Pageable pageable);

    Page<Expense> findByUserIdAndAmountBetween(Long userId, Double minAmount, Double maxAmount, Pageable pageable);

    Page<Expense> findByUserIdAndDateBetween(Long userId, LocalDate minDate, LocalDate maxDate, Pageable pageable);


}
