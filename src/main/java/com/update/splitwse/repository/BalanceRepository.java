package com.update.splitwse.repository;

import com.update.splitwse.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    // Find balances by user ID
    List<Balance> findByUserIdOwedToOrUserIdOwedFrom(Long userIdOwedTo, Long userIdOwedFrom);

    // Find balance between two users for a specific group
    Balance findByUserIdOwedToAndUserIdOwedFromAndGroupId(Long userIdOwedTo, Long userIdOwedFrom, Long groupId);
}
