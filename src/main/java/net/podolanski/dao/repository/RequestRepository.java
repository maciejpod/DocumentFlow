/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao.repository;

import java.util.List;
import net.podolanski.dao.Request;
import net.podolanski.dao.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maciej
 */
public interface RequestRepository extends CrudRepository<Request, Integer> {

    List<Request> findFirst4ByUser(User user);

    @Query("SELECT cs.request FROM CurrentState cs"
            + " JOIN cs.transaction t JOIN t.roleId ro JOIN ro.userroleCollection ur"
            + " WHERE ur.user = ?1 AND ur.department = cs.department")
    List<Request> findToProced(User user, Pageable pageable);
    List<Request> findByUser(User user);
    
    @Query("SELECT cs.request FROM CurrentState cs"
            + " JOIN cs.transaction t JOIN t.roleId ro JOIN ro.userroleCollection ur"
            + " WHERE ur.user = ?1 AND ur.department = cs.department AND cs.request.requestId = ?2")
    Request findRequestToProceed(User user, Integer id);
    Request findByRequestIdAndUser(Integer requestId, User user);
}
