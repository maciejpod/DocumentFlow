/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.controller;

import net.podolanski.dao.User;
import net.podolanski.dto.PasswordChange;
import net.podolanski.dto.UserForm;
import net.podolanski.service.UserService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maciej
 */
@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    Mapper mapper;

    @GetMapping("/{user}")
    public ModelAndView initUserForm(@PathVariable User user) {
        ModelAndView mav = new ModelAndView("userdetails");
        UserForm userForm = mapper.map(user, UserForm.class);
        mav.addObject("userDetails", userForm);
        mav.addObject("passwordForm", new PasswordChange());
        return mav;
    }

    //dokonczyc
    @PostMapping("/{user}/edit")
    public String processUserForm(@PathVariable User user,
            UserForm userForm, PasswordChange passwordChange) {
        User newUser = mapper.map(userForm, User.class);
        newUser.setUserId(user.getUserId());
        logger.info(newUser.toString());
        return "redirect:/user";
    }

    @GetMapping("/new")
    public ModelAndView initNewUserForm() {
        ModelAndView mav = new ModelAndView("newUser");
        mav.addObject("userForm", new UserForm());
        return mav;
    }

    @PostMapping("/new")
    public String processNewUserForm(UserForm userForm) {
        User user = mapper.map(userForm, User.class);
        logger.info(user.toString());
        //userService.save(user);
        return "redirect:/login";
    }

}
