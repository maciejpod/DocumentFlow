/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.controller;

import net.podolanski.service.RequestService;
import net.podolanski.dao.User;
import net.podolanski.dao.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maciej
 */
@Controller
public class HomeController {

    @Autowired RequestService requestService;
    @Autowired UserRepository ur;

    @RequestMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/home")
    ModelAndView home(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("requestList", requestService.findAll(user));
        mav.addObject("proceedList", requestService
                .findRequestToProceed(user));
        return mav;
    }
}
