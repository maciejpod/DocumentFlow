/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.podolanski.dao.Connection;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Transaction;
import net.podolanski.dao.repository.ConnectionRepository;
import net.podolanski.dao.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maciej
 */
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ConnectionRepository connectionRepository;

    List<Transaction> getDocumentFlow(Doctype doctype) {        
        return getSortedConnections(doctype)
                .stream()
                .map((n) -> n.getTransaction())
                .collect(Collectors.toList());
    }
    
    private List<Connection> getSortedConnections(Doctype doctype) {
        List<Connection> connectionList = connectionRepository.findByDoctype(doctype);
        
        Comparator<Connection> comparator = (o1, o2) -> {
            return o1.getTransaction().equals(doctype.getTransactionId()) ? -1: 1;
        };    
        connectionList.sort(comparator.thenComparing((o1, o2) -> {
            return o1.getTransaction1().equals(o2.getTransaction()) ? 1: -1;
        }));
        return  connectionList;
    }

}
