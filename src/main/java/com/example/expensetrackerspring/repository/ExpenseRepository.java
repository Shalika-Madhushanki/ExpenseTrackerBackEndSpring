package com.example.expensetrackerspring.repository;


import com.example.expensetrackerspring.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Override
    Page<Expense> findAll(Pageable pageable);
}
