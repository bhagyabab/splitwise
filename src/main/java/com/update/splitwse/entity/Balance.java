package com.update.splitwse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balanceId;

    private Long userIdOwedTo;   // The user who is owed money
    private Long userIdOwedFrom; // The user who owes money
    private Long groupId;
    private Double amountOwedTo;
    private Double amountOwedFrom;

    // Getters and Setters
    public Long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    public Long getUserIdOwedTo() {
        return userIdOwedTo;
    }

    public void setUserIdOwedTo(Long userIdOwedTo) {
        this.userIdOwedTo = userIdOwedTo;
    }

    public Long getUserIdOwedFrom() {
        return userIdOwedFrom;
    }

    public void setUserIdOwedFrom(Long userIdOwedFrom) {
        this.userIdOwedFrom = userIdOwedFrom;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Double getAmountOwedTo() {
        return amountOwedTo;
    }

    public void setAmountOwedTo(Double amountOwedTo) {
        this.amountOwedTo = amountOwedTo;
    }

    public Double getAmountOwedFrom() {
        return amountOwedFrom;
    }

    public void setAmountOwedFrom(Double amountOwedFrom) {
        this.amountOwedFrom = amountOwedFrom;
    }
}
