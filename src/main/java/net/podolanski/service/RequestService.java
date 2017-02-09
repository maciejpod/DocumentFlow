/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import java.util.List;
import net.podolanski.dao.Request;
import net.podolanski.dao.Status;
import net.podolanski.dao.User;
import net.podolanski.dao.repository.DepartmentRepository;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dao.repository.RequestRepository;
import net.podolanski.dao.repository.UserRepository;
import net.podolanski.dto.NewRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

/**
 *
 * @author maciej
 */
@Service
public class RequestService {
    
    @Autowired
    RequestRepository  requestRepository;
    @Autowired
    CurrentStateService currentStateService;
    
    @Autowired
    DoctypeRepository doctypeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserRepository userRepository;
    
    //user id // doctype // status id // department id // content
            
    private final Status defualtStatus = Status.W_trakcie;
    
    void createNewRequest(NewRequestForm newRequestForm) {
    /*    
        User user = (User)authentication.getPrincipal();
        Department department = departmentRepository.findOne(2);
        Doctype doctype = doctypeRepository.findOne(1);
        
        Request request = new Request(user, "content", department, defualtStatus, doctype);
        
        requestRepository.save(request);
        currentStateService.intializeFlow(request);*/
    }
    
    public List<Request> findAll(User user) {
        return requestRepository.findFirst4ByUserId(user);
    }
    
    public void update(Request request) {
        requestRepository.save(request);
        currentStateService.intializeFlow(request);
    }
    
}