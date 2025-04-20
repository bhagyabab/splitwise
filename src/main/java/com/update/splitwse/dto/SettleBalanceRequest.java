package com.update.splitwse.dto;  // Make sure this matches your project's package structure

public class SettleBalanceRequest {

    private Long owedTo;
    private Double amount;
    private Long groupId;

    // Default constructor
    public SettleBalanceRequest() {
    }

    // Getters and Setters
    public Long getOwedTo() {
        return owedTo;
    }

    public void setOwedTo(Long owedTo) {
        this.owedTo = owedTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
