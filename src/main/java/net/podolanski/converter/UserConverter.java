/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.converter;

import net.podolanski.dao.User;
import net.podolanski.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author maciej
 */
@Component
public class UserConverter implements Converter<String, User>{

    @Autowired
    UserRepository userRepository;
    
    @Override
    public User convert(String username) {
        return userRepository.findByUsername(username);
    }
    
}
