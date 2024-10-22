package com.example.expensetrackerspring.controller;

import com.example.expensetrackerspring.dto.CreateExpenseRequest;
import com.example.expensetrackerspring.dto.ExpenseResponse;
import com.example.expensetrackerspring.exception.ResourceNotFoundException;
import com.example.expensetrackerspring.model.Expense;
import com.example.expensetrackerspring.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/expenses")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<Expense> getAllExpenseRecords() {
        log.info("Listing All expense records." );
        return expenseService.findAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense =  expenseService.findExpenseById(id);
        log.info("Listing the expense record with id: {}", id );
        return expense.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Expense Record with ID " + id + " not found!"));
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody CreateExpenseRequest expenseRequest) {
        Expense createdExpense = expenseService.addExpense(expenseRequest);
        log.info("Successfully created expense with id: {} ",createdExpense.getId());
        return new ResponseEntity<>(modelMapper.map(createdExpense, ExpenseResponse.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        try {
            Expense updatedExpense = expenseService.updateExpense(id, expense);
            log.info("Successfully updated expense with id: {} ",id );
            return ResponseEntity.ok(updatedExpense);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        log.info("Successfully deleted user with id: {} ",id );
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public Page<Expense> getPagedExpenses(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        log.info("Listing expense records for page: {}, size: {}", page, size );
        return expenseService.findAllByPage(pageable);
    }
}
