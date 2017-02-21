/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.controller;

import javax.validation.Valid;
import net.podolanski.dao.CurrentState;
import net.podolanski.dao.Department;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Request;
import net.podolanski.dao.Status;
import net.podolanski.dao.User;
import net.podolanski.dao.repository.DepartmentRepository;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dto.ChangeStateForm;
import net.podolanski.dto.NewRequestForm;
import net.podolanski.service.CurrentStateService;
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
    
    @Autowired DoctypeRepository doctypeRepository;             
    @Autowired DepartmentRepository departmentRepository;       
    @Autowired RequestService requestService;
    @Autowired CurrentStateService currentStateService;
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

    @PostMapping("/{id}/cancel")
    String cancelRequest(User user, @PathVariable Integer id) {
        Request request = requestService.findByIdAndUser(id, user);
        requestService.cancelRequest(request);
        return "redirect:/{user}/request/{id}";
    }

    @PostMapping("/proceed/{id}/edit")
    String proessChangeStateForm(User user, @PathVariable Integer id,
            ChangeStateForm changeStateForm) {
        Request request = requestService.findRequestToProceed(user, id);
        request.setFeedback(changeStateForm.getFeedback());
        CurrentState cs = currentStateService.findByUserAndRequest(user, request);
        cs.setStatusId(changeStateForm.getStatus());
        currentStateService.update(cs);
        return "redirect:/{user}/request/proceed/";
        
    }

    @GetMapping("/")
    ModelAndView getRequestList(User user) {
        ModelAndView mav = new ModelAndView("request-list");
        mav.addObject("requestList", requestService.findAll(user));
        return mav;
    }

    @GetMapping("/proceed")
    ModelAndView getRequestToProceedList(User user) {
        ModelAndView mav = new ModelAndView("proceed-list");
        mav.addObject("proceedList", requestService.findRequestToProceed(user));
        return mav;
    }

    @GetMapping("/proceed/{id}")
    ModelAndView getRequestToProceedDetails(User user, @PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("proceed-details");
        Request request = requestService.findRequestToProceed(user, id);
        mav.addObject("changeStateForm", new ChangeStateForm());
        mav.addObject("currentState", currentStateService.findFirst(request));
        mav.addObject("request", request);
        mav.addObject("statusList", Status.values());
        mav.addObject("transactionList",
                transactionService.getDocumentFlow(request.getDocType()));
        return mav;
    }

    @GetMapping("/{id}")
    ModelAndView getRequestDetails(User user, @PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("request-details");
        Request request = requestService.findByIdAndUser(id, user);
        mav.addObject("currentState", currentStateService.findFirst(request));
        mav.addObject("request", request);
        mav.addObject("transactionList",
                transactionService.getDocumentFlow(request.getDocType()));
        return mav;
    }
}
