/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.controller;

import javax.validation.Valid;
import net.podolanski.dao.Department;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Request;
import net.podolanski.dao.User;
import net.podolanski.dao.repository.DepartmentRepository;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dto.NewRequestForm;
import net.podolanski.service.RequestService;
import net.podolanski.service.TransactionService;
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
@RequestMapping("/{user}/request/")
public class RequestController {

    Logger log = LoggerFactory.getLogger(RequestController.class);

    @Autowired DoctypeRepository doctypeRepository;
    @Autowired DepartmentRepository departmentRepository;
    @Autowired RequestService requestService;
    @Autowired TransactionService transactionService;
    @Autowired Mapper mapper;

    @PreAuthorize("isAuthenticated()")
    @ModelAttribute("docList")
    Iterable<Doctype> getDocTypes() {
        return doctypeRepository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @ModelAttribute("depList")
    Iterable<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/new")
    ModelAndView initNewRequestForm(User user) {
        ModelAndView mav = new ModelAndView("new-request");
        mav.addObject("newRequestForm", new NewRequestForm());
        return mav;
    }

    @PostMapping("/new")
    String processNewRequestForm(User user, @Valid NewRequestForm newRequestForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "new-request";
        }
        Request request = mapper.map(newRequestForm, Request.class);
        request.setUser(user);
        requestService.update(request);
        return "redirect:/home";
    }

    @GetMapping("/")
    ModelAndView getRequestList(@PathVariable User user) {
        ModelAndView mav = new ModelAndView("request-list");
        mav.addObject("requestList", requestService.findAll(user));
        return mav;
    }

    @GetMapping("/proceed")
    ModelAndView getRequestToProceedList(@PathVariable User user) {
        ModelAndView mav = new ModelAndView("proceed-list");
        mav.addObject("proceedList", requestService.findRequestToProceed(user));
        return mav;
    }

    @GetMapping("/{id}")
    ModelAndView getRequestDetails(/*@PathVariable*/User user) {
        ModelAndView mav = new ModelAndView("request-details");
        Request request = requestService.findAll(user).get(0);
        mav.addObject("request", request);
        mav.addObject("transactionList",
                transactionService.getDocumentFlow(request.getDocType()));
        return mav;
    }
}
