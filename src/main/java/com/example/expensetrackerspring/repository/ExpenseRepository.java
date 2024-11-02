package com.example.expensetrackerspring.repository;


import com.example.expensetrackerspring.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Override
    Page<Expense> findAll(Pageable pageable);

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
