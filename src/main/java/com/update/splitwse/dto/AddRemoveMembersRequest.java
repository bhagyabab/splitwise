package com.update.splitwse.dto;

import java.util.List;

public class AddRemoveMembersRequest {

    private List<Long> userIdsToAdd;
    private List<Long> userIdsToRemove;

    // Getters and Setters
    public List<Long> getUserIdsToAdd() {
        return userIdsToAdd;
    }

    public void setUserIdsToAdd(List<Long> userIdsToAdd) {
        this.userIdsToAdd = userIdsToAdd;
    }

    public List<Long> getUserIdsToRemove() {
        return userIdsToRemove;
    }

    public void setUserIdsToRemove(List<Long> userIdsToRemove) {
        this.userIdsToRemove = userIdsToRemove;
    }
}
