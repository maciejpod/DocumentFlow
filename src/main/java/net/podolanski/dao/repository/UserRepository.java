/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao.repository;

import net.podolanski.dao.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maciej
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByUsername(String username);
    User findByEmail(String email);
}
