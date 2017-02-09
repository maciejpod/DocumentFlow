/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao.repository;

import net.podolanski.dao.CurrentState;
import net.podolanski.dao.Request;

import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;
import net.podolanski.dao.CurrentStatePK;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author maciej
 */
public interface CurrentStateRepository extends CrudRepository<CurrentState, Integer>{
    
    @Transactional
    Long deleteByRequest(Request request);
    
    List<CurrentState> findByRequest(Request request);
    
}
