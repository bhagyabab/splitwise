package com.update.splitwse.service;

import com.update.splitwse.dto.SettleBalanceRequest;
import com.update.splitwse.entity.Balance;
import com.update.splitwse.repository.BalanceRepository;
import com.update.splitwse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private UserRepository userRepository;

    // Get user balances
    public List<Balance> getUserBalances(Long userId) {
        return balanceRepository.findByUserIdOwedToOrUserIdOwedFrom(userId, userId);
    }

    // Settle balance between users
    public void settleBalance(Long userId, SettleBalanceRequest settleRequest) {
        // Logic for settling the balance. For example:
        Long owedToUserId = settleRequest.getOwedTo();
        Double amount = settleRequest.getAmount();
        Long groupId = settleRequest.getGroupId();

        // Fetch the balance between these two users and update it
        Balance balance = balanceRepository.findByUserIdOwedToAndUserIdOwedFromAndGroupId(owedToUserId, userId, groupId);

        if (balance != null) {
            // Update balance logic here
            balance.setAmountOwedFrom(balance.getAmountOwedFrom() - amount);
            balance.setAmountOwedTo(balance.getAmountOwedTo() + amount);
            balanceRepository.save(balance);
        } else {
            throw new RuntimeException("No balance found to settle.");
        }
    }
}
