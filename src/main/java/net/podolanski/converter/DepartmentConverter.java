/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.converter;

import net.podolanski.dao.Department;
import net.podolanski.dao.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author maciej
 */

@Component
public class DepartmentConverter implements Converter<String, Department>{

    @Autowired
    DepartmentRepository departmentRepository;
    
    @Override
    public Department convert(String s) {
        return departmentRepository.findByName(s);
    }
    
}
