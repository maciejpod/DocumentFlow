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
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author maciej
 */
@Service
public class RequestService {
    
    @Autowired RequestRepository  requestRepository;
    @Autowired CurrentStateService currentStateService;
    @Autowired DoctypeRepository doctypeRepository;
    @Autowired DepartmentRepository departmentRepository;
    @Autowired UserRepository userRepository;
    
    public List<Request> findAll(User user) {
        return requestRepository.findByUser(user);
    }
    
    public Request findRequestToProceed(User user, Integer id) {
        return requestRepository.findRequestToProceed(user, id);
    }
    
    public void cancelRequest(Request request) {
        request.setStatusId(Status.CANCELLED);
        update(request);
        currentStateService.deleteByRequest(request);
    }
    
    public List<Request> findRequestToProceed(User user) {
        return requestRepository.findToProced(user, null);
    }
    
    public void update(Request request) {
        requestRepository.save(request);
        if(request.getStatusId().equals(Status.PROCESSING))
            currentStateService.intializeFlow(request);
    }   
    
    public Request findByIdAndUser(Integer id, User user) {
        return requestRepository.findByRequestIdAndUser(id, user);
    }
}
