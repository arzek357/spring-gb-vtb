package com.vtb.zolotarev.homeWork5.services;

import com.vtb.zolotarev.homeWork5.entity.User;
import com.vtb.zolotarev.homeWork5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }
}
