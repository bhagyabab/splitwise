package com.update.splitwse.controller;

import com.update.splitwse.dto.AddRemoveMembersRequest;
import com.update.splitwse.dto.GroupRequest;
import com.update.splitwse.entity.Group;

import com.update.splitwse.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Create a new group
    @PostMapping
    public ResponseEntity<Map<String, Object>> createGroup(@RequestBody GroupRequest groupRequest) {
        // Call the service to create the group
        Group createdGroup = groupService.createGroup(groupRequest.getGroupName(), groupRequest.getUserIds());

        // Prepare the response map
        Map<String, Object> response = new HashMap<>();
        response.put("groupId", createdGroup.getGroupId());
        response.put("status", "Group created successfully!");

        return ResponseEntity.ok(response);
    }

    // Add/Remove members to/from a group
    @PutMapping("/{groupId}/members")
    public ResponseEntity<Map<String, Object>> addRemoveMembers(
            @PathVariable Long groupId,
            @RequestBody AddRemoveMembersRequest request) {

        Group updatedGroup = groupService.addRemoveMembers(groupId, request.getUserIdsToAdd(), request.getUserIdsToRemove());

        // Prepare the response map
        Map<String, Object> response = new HashMap<>();
        response.put("groupId", updatedGroup.getGroupId());
        response.put("members", updatedGroup.getMembers());
        response.put("status", "Members updated successfully!");

        return ResponseEntity.ok(response);
    }

    // Get group details
    @GetMapping("/{groupId}")
    public ResponseEntity<Map<String, Object>> getGroupDetails(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);

        // Prepare the response map
        Map<String, Object> response = new HashMap<>();
        response.put("groupId", group.getGroupId());
        response.put("groupName", group.getGroupName());
        response.put("members", group.getMembers());
        response.put("expenses", group.getExpenses()); // Assuming you have expenses in the Group entity

        return ResponseEntity.ok(response);
    }
}
