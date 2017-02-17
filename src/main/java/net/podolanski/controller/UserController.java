/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.controller;

import javax.validation.Valid;
import net.podolanski.dao.User;
import net.podolanski.dto.PasswordChange;
import net.podolanski.dto.UserForm;
import net.podolanski.service.UserService;
import net.podolanski.validator.PasswordValidator;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired UserService userService;
    @Autowired Mapper mapper;
    @Autowired PasswordValidator passwordValidator;

    @ModelAttribute("passwordChange")
    PasswordChange getPasswordForm() {
        return new PasswordChange();
    }

    @ModelAttribute("userDetails")
    UserForm getUserForm(User user) {
        return mapper.map(user, UserForm.class);
    }

    @GetMapping("/{user}")
    @PreAuthorize("principal.username == #user.username")
    public ModelAndView initUserForm(User user) {
        ModelAndView mav = new ModelAndView("user-details");
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView initNewUserForm() {
        ModelAndView mav = new ModelAndView("newUser");
        mav.addObject("userForm", new UserForm());
        return mav;
    }

    @PostMapping("/{user}/password/edit")
    public String processUserForm(User user, @Valid PasswordChange passwordChange,
            BindingResult bindingResult) {

        passwordChange.setUserPassword(user.getPassword());
        passwordValidator.validate(passwordChange, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user-details";
        }
        return "redirect:/user/{user}";
    }

    @PostMapping("/{user}/edit")
    @PreAuthorize("principal.username == #user.username")
    public String processUserForm(User user,
            UserForm userForm) {
        User newUser = mapper.map(userForm, User.class);
        newUser.setUserId(user.getUserId());
        logger.info(newUser.toString());
        return "redirect:/user";
    }

    @PostMapping("/new")
    public String processNewUserForm(UserForm userForm) {
        User user = mapper.map(userForm, User.class);
        logger.info(user.toString());
        //userService.save(user);
        return "redirect:/login";
    }

}
