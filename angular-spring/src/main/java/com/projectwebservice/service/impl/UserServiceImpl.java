package com.projectwebservice.service.impl;

import com.projectwebservice.helper.UserFoundException;
import com.projectwebservice.model.Role;
import com.projectwebservice.model.User;
import com.projectwebservice.model.UserRole;
import com.projectwebservice.repo.RoleRepository;
import com.projectwebservice.repo.UserRepository;
import com.projectwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local=this.userRepository.findByUsername(user.getUsername());
        if (local!=null){
            System.out.println("User is already there !!");
            throw new UserFoundException();
        }else {
            for (UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local= this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
