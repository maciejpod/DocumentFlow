/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import java.util.List;
import net.podolanski.dao.User;
import net.podolanski.dao.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author maciej
 */
@Service
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + userName);
        } else {
            List<String> userRole = userRoleService.userRoleAsString(user);
            return new UserDetailsImplementation(userRole, user);
        }
    }

}
