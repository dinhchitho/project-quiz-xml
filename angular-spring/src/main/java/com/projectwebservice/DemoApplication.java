package com.projectwebservice;

import com.projectwebservice.markxml.ListUser;
import com.projectwebservice.model.Role;
import com.projectwebservice.model.User;
import com.projectwebservice.model.UserRole;
import com.projectwebservice.model.exam.Quiz;
import com.projectwebservice.repo.MarkRepository;
import com.projectwebservice.service.MarkService;
import com.projectwebservice.service.QuizService;
import com.projectwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private MarkService markService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ListUser userList = markService.getAllMark();
        ListUser listUser = markService.marshalling(userList);
        System.out.println("userList"+listUser);
        //Quiz quiz = quizService.importQuizXml();
//        User user=new User();
//        user.setFirstName("admin");
//        user.setLastName("nguyen");
//        user.setUsername("admin");
//        user.setPassword(this.bCryptPasswordEncoder.encode("123456"));
//        user.setEmail("admin@gmail.com");
//        user.setProfile("default.png");
//        Role role=new Role();
//        role.setRoleId(44L);
//        role.setRoleName("ADMIN");
//        Set<UserRole> userRoleSet=new HashSet<>();
//        UserRole userRole=new UserRole();
//        userRole.setRole(role);
//        userRole.setUser(user);
//        userRoleSet.add(userRole);
//        User user1=this.userService.createUser(user,userRoleSet);
//        System.out.println("User:"+user1);
    }
}
