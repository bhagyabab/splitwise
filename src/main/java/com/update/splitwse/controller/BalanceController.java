package com.update.splitwse.controller;

import com.update.splitwse.service.BalanceService;
import com.update.splitwse.dto.SettleBalanceRequest;
import com.update.splitwse.entity.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    // Get user balances
    @GetMapping("/{userId}/balances")
    public ResponseEntity<List<Balance>> getUserBalances(@PathVariable Long userId) {
        List<Balance> balances = balanceService.getUserBalances(userId);
        if (balances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(balances, HttpStatus.OK);
    }

    // Settle user balances
    @PostMapping("/{userId}/settle")
    public ResponseEntity<String> settleBalance(@PathVariable Long userId,
                                                @RequestBody SettleBalanceRequest settleRequest) {
        try {
            balanceService.settleBalance(userId, settleRequest);
            return new ResponseEntity<>("Balance settled successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error settling balance", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
