package com.update.splitwse.service;

import com.update.splitwse.dto.ExpenseRequest;
import com.update.splitwse.entity.Expense;
import com.update.splitwse.entity.Group;
import com.update.splitwse.entity.User;
import com.update.splitwse.repository.ExpenseRepository;
import com.update.splitwse.repository.GroupRepository;
import com.update.splitwse.repository.UserRepository;
import com.update.splitwse.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;  // Added repository for Group

    // Add expense to the group
    public void addExpense(ExpenseRequest expenseRequest) {
        Double amount = expenseRequest.getAmount();
        Long groupId = expenseRequest.getGroupId();  // This is still needed for fetching the Group
        List<Long> splitAmong = expenseRequest.getSplitAmong();
        String description = expenseRequest.getDescription();
        String dateString = expenseRequest.getDate();

        // Convert string to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        // Fetch Group entity by ID
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));

        // Fetch User entities for the users in the splitAmong list
        if (splitAmong == null || splitAmong.isEmpty()) {
            throw new IllegalArgumentException("No users selected for expense splitting.");
        }

        List<User> users = userRepository.findAllById(splitAmong);

        // Check if the number of users found matches the number of IDs provided
        if (users.size() != splitAmong.size()) {
            throw new ResourceNotFoundException("One or more users not found.");
        }

        // Create and save the expense
        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setGroup(group);  // Set the Group object directly
        expense.setDescription(description);
        expense.setDate(date); // Set as LocalDate
        expense.setUsers(users);

        expenseRepository.save(expense);
    }

    // Get expense by ID and groupId
    public Expense getExpenseById(Long groupId, Long expenseId) {
        return expenseRepository.findByGroup_GroupIdAndExpenseId(groupId, expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found for the given group and expenseId"));
    }

    // List all expenses for a group
    public List<Expense> listAllExpenses(Long groupId) {
        return expenseRepository.findByGroup_GroupId(groupId);
    }
}