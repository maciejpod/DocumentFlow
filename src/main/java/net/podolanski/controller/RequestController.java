/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.controller;

import net.podolanski.dao.repository.DepartmentRepository;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dto.NewRequestForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maciej
 */
@Controller
@RequestMapping("/request")
public class RequestController {
    
    Logger log = LoggerFactory.getLogger(RequestController.class);
    
    @Autowired
    DoctypeRepository doctypeRepository;
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    @GetMapping("/new")
    ModelAndView initNewRequestForm() {
        ModelAndView mav = new ModelAndView("requestform");
        mav.addObject("requestForm", new NewRequestForm());
        mav.addObject("depList", departmentRepository.findAll());
        mav.addObject("docList", doctypeRepository.findAll());
        return mav;
    }
    
    @PostMapping("/new")
    String processNewRequestForm(NewRequestForm requestForm) {
        log.info(requestForm.getContent());
        log.info(requestForm.getDocType().getName());
        log.info(requestForm.getDepartment().getName());
        return "redirect:/home";
    }
}
