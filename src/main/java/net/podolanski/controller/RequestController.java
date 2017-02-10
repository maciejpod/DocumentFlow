/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.controller;

import net.podolanski.dao.Request;
import net.podolanski.dao.User;
import net.podolanski.dao.repository.DepartmentRepository;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dto.NewRequestForm;
import net.podolanski.service.RequestService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/{user}/request/")
public class RequestController {

    Logger log = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    DoctypeRepository doctypeRepository;

    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    RequestService requestService;

    @Autowired
    Mapper mapper;
    
    @GetMapping("/new")
    ModelAndView initNewRequestForm() {
        ModelAndView mav = new ModelAndView("requestform");
        mav.addObject("requestForm", new NewRequestForm());
        mav.addObject("depList", departmentRepository.findAll());
        mav.addObject("docList", doctypeRepository.findAll());
        return mav;
    }

    @PostMapping("/new")
    String processNewRequestForm(@PathVariable User user, NewRequestForm requestForm) {
        Request request = mapper.map(requestForm, Request.class);
        request.setUser(user);
        requestService.update(request);
        return "redirect:/home";
    }
    
    @GetMapping("/")
    ModelAndView getRequestList(@PathVariable User user) {
       ModelAndView mav = new ModelAndView("requestList");
       mav.addObject("requestList", requestService.findAll(user));
       return mav;
    }
}
