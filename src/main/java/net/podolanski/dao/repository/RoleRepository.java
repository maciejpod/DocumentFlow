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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maciej
 */
public interface RoleRepository extends CrudRepository<Role, Integer>{
    
    @Query("SELECT ur.role FROM  Userrole ur WHERE ur.user = ?1 and ur.department = ?2")
    List<Role> findRoleByDepartment(User user, Department department);

    Role findRoleByName(String name);
}
