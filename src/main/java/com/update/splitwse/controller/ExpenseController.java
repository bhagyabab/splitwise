package com.update.splitwse.controller;

import com.update.splitwse.dto.ExpenseRequest;
import com.update.splitwse.entity.Expense;
import com.update.splitwse.service.ExpenseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups/{groupId}/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Add expense to a group
    @PostMapping
    public ResponseEntity<String> addExpense(@PathVariable Long groupId, @RequestBody ExpenseRequest expenseRequest) {
        try {
            expenseRequest.setGroupId(groupId);
            expenseService.addExpense(expenseRequest);
            return new ResponseEntity<>("Expense added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding expense", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get expense details
    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpenseDetails(@PathVariable Long groupId, @PathVariable Long expenseId) {
        try {
            Expense expense = expenseService.getExpenseById(groupId, expenseId);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // List all expenses for a group
    @GetMapping
    public ResponseEntity<List<Expense>> listAllExpenses(@PathVariable Long groupId) {
        try {
            List<Expense> expenses = expenseService.listAllExpenses(groupId);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
