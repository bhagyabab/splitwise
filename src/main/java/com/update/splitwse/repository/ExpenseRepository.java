package com.update.splitwse.repository;

import com.update.splitwse.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByGroup_GroupId(Long groupId);

    Optional<Expense> findByGroup_GroupIdAndExpenseId(Long groupId, Long expenseId);
}
