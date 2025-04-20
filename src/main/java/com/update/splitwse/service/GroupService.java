package com.update.splitwse.service;

import com.update.splitwse.entity.Group;
import com.update.splitwse.entity.User;
import com.update.splitwse.exception.ResourceNotFoundException;
import com.update.splitwse.repository.GroupRepository;
import com.update.splitwse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new group
    public Group createGroup(String groupName, List<Long> memberIds) {
        Group group = new Group();
        group.setGroupName(groupName);

        // Fetch users by their IDs
        List<User> members = userRepository.findAllById(memberIds);
        group.setMembers(members);

        return groupRepository.save(group);
    }

    // Add/Remove members to/from a group
    public Group addRemoveMembers(Long groupId, List<Long> userIdsToAdd, List<Long> userIdsToRemove) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + groupId));

        // Fetch users to add/remove
        List<User> usersToAdd = userRepository.findAllById(userIdsToAdd);
        List<User> usersToRemove = userRepository.findAllById(userIdsToRemove);

        // Add members
        group.getMembers().addAll(usersToAdd);

        // Remove members
        group.getMembers().removeAll(usersToRemove);

        return groupRepository.save(group);
    }

    // Get a group by ID
    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + groupId));
    }
}
