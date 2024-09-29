package com.example.expensetrackerspring.service;

import com.example.expensetrackerspring.model.Expense;
import com.example.expensetrackerspring.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    public List<Expense> findAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> findExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    public Expense updateExpense(Long id, Expense updatedExpense){
        Expense expense = expenseRepository.findById(id).orElseThrow();
        expense.setDescription(updatedExpense.getDescription());
        expense.setComments(updatedExpense.getComments());
        expense.setCategory(updatedExpense.getCategory());
        expense.setPaidBy(updatedExpense.getPaidBy());
        expense.setAmount(updatedExpense.getAmount());
        return expenseRepository.save(updatedExpense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Page<Expense> findAllByPage(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

}
