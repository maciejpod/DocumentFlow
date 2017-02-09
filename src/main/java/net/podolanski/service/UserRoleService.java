/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import java.util.List;
import java.util.stream.Collectors;
import net.podolanski.dao.User;
import net.podolanski.dao.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maciej
 */
@Service
public class UserRoleService {
    
    @Autowired
    UserRoleRepository userRoleRepository;
    
    List<String> userRoleAsString(User user) {
        List<String> stringResult = userRoleRepository
                                        .findByUser(user)
                                        .stream()
                                        .map((n)-> n.getRole().getName())
                                        .collect(Collectors.toList());
        return stringResult;                                                   
    }
    
}
