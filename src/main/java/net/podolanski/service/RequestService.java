/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import java.util.List;
import net.podolanski.dao.Request;
import net.podolanski.dao.User;
import net.podolanski.dao.repository.DepartmentRepository;
import net.podolanski.dao.repository.DoctypeRepository;
import net.podolanski.dao.repository.RequestRepository;
import net.podolanski.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
    
    public List<Request> findAll(User user) {
        return requestRepository.findByUser(user);
    }
    
    public List<Request> findRequestToProceed(User user) {
        return requestRepository.findToProced(user, null);
    }
    
    public void update(Request request) {
        //usunac ?
        requestRepository.save(request);
        currentStateService.intializeFlow(request);
    }        
}
