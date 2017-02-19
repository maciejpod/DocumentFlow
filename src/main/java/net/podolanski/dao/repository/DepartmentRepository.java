/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao.repository;

import net.podolanski.dao.Department;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maciej
 */
public interface DepartmentRepository extends CrudRepository<Department, Integer>{
    
    Department findByName(String name);

}
