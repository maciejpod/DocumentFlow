/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.podolanski.dao.Department;
import net.podolanski.dao.Role;
import net.podolanski.dao.User;
import net.podolanski.dao.Userrole;
import net.podolanski.dao.repository.RoleRepository;
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

    @Autowired
    RoleRepository roleRepository;

    List<String> userRoleAsString(User user) {
        List<String> stringResult = userRoleRepository
                .findByUser(user)
                .stream()
                .map((n) -> n.getRole().getName())
                .collect(Collectors.toList());
        return stringResult;
    }

    public Map<Department, List<Role>> getDepartmentRoles(User user) {
        Map<Department, List<Role>> resultMap = new HashMap<>();
        List<Userrole> userRoleList = userRoleRepository.findByUser(user);
        resultMap = userRoleList.stream()
                .collect(Collectors.toMap(Userrole::getDepartment,
                        (userrole) -> {
                            return roleRepository.findRoleByDepartment(user, userrole.getDepartment());
                        }));
        return resultMap;
    }

    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }
}
