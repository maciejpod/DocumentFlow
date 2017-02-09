/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao.repository;

import java.util.List;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author maciej
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
    
    @Query("SELECT con.transaction1 FROM Connection "
            + "con WHERE con.transaction = ?1 AND con.doctype = ?2")
    Transaction findNextTransaction(Transaction transaction, Doctype doctype);
    
    @Query("SELECT dt.transactionId FROM Doctype "
            + "dt WHERE dt.doctypeId = ?1")
    Transaction findFirstTransaction(Integer doctypeId);
}
