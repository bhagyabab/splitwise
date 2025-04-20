package com.update.splitwse.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class ExpenseRequest {

	@NotNull
    private Double amount;
    private Long groupId;
    private List<Long> splitAmong;
    private String description;
    private String date;

    // Getters and Setters
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

    public List<Long> getSplitAmong() {
        return splitAmong;
    }

    public void setSplitAmong(List<Long> splitAmong) {
        this.splitAmong = splitAmong;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
