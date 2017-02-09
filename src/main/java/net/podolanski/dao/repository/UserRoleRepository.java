/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao.repository;

import java.util.List;
import net.podolanski.dao.Department;
import net.podolanski.dao.Role;
import net.podolanski.dao.User;
import net.podolanski.dao.Userrole;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maciej
 */ 
public interface UserRoleRepository extends CrudRepository<Userrole, Integer>{
    List<Userrole> findByRoleAndDepartment(Role role, Department department);
    List<Userrole> findByUser(User user);
}
