/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao.repository;

import net.podolanski.dao.CurrentState;
import net.podolanski.dao.Request;
import java.util.List;
import javax.transaction.Transactional;
import net.podolanski.dao.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maciej
 */
public interface CurrentStateRepository extends CrudRepository<CurrentState, Integer> {

    @Query("SELECT cs FROM CurrentState cs"
            + " JOIN cs.transaction t JOIN t.roleId ro JOIN ro.userroleCollection ur"
            + " WHERE ur.user = ?1 AND ur.department = cs.department AND cs.request = ?2")
    CurrentState findToProcedByUserAndRequest(User user, Request request);

    @Transactional
    Long deleteByRequest(Request request);

    List<CurrentState> findByRequest(Request request);
    
    CurrentState findFirstByRequest(Request request);
}
