/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import net.podolanski.dao.User;
import net.podolanski.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author maciej
 */
public class UserService {
    
    @Autowired
    UserRepository userRepository;
    
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
