/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.controller;

import net.podolanski.dao.User;
import net.podolanski.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maciej
 */
@Controller
@RequestMapping("/{user}/role/")
@PreAuthorize("principal.username == #user.username")
public class RoleController {
          
    @Autowired
    UserRoleService userRoleService;
    
    @GetMapping("/")
    ModelAndView getUserRoles(User user) {
        ModelAndView mav = new ModelAndView("user-departments");
        mav.addObject("userRole", userRoleService.getDepartmentRoles(user));
        return mav;
    }
    
}
