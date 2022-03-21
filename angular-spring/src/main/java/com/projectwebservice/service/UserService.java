package com.projectwebservice.service;

import com.projectwebservice.model.Role;
import com.projectwebservice.model.User;
import com.projectwebservice.model.UserRole;

import java.util.Set;

public interface UserService {
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(String username);
    public void deleteUser(Long userId);
}
