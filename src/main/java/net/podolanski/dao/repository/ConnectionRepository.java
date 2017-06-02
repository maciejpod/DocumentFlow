/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao.repository;

import java.util.List;
import net.podolanski.dao.Connection;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Transaction;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maciej
 */
public interface ConnectionRepository extends CrudRepository<Connection, Integer> {
    List<Connection> findByDoctype(Doctype doctype);

    Connection findByDoctypeAndTransaction(Doctype doctype, Transaction transaction);
}
