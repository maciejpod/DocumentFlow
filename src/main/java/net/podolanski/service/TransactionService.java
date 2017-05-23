/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.podolanski.dao.Connection;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Transaction;
import net.podolanski.dao.repository.ConnectionRepository;
import net.podolanski.dao.repository.TransactionRepository;
import net.podolanski.dto.DocumentPathForm;
import net.podolanski.dto.PathElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maciej
 */
@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ConnectionRepository connectionRepository;

    public List<Transaction> getDocumentFlow(Doctype doctype) {        
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

    public void createNewDocumentPath(DocumentPathForm documentPathForm) {
        List<PathElement> pathElementList = documentPathForm.getPathElementList();
        Doctype docType = null;
        Iterator<PathElement> pathElementIterator = pathElementList.iterator();
        PathElement prevPathElement = pathElementIterator.next();

        while(pathElementIterator.hasNext()) {
            PathElement currentPathElement = pathElementIterator.next();
            Transaction prevTransaction = buildTransaction(prevPathElement);
            if(docType == null) {
                docType = new Doctype();
                docType.setName(documentPathForm.getDocumentName());
                docType.setTransactionId(prevTransaction);
            }
            Transaction nextTransaction = buildTransaction(currentPathElement);
            Connection connection = new Connection(docType, prevTransaction, nextTransaction);
            prevPathElement = currentPathElement;
        }
        Transaction prevTransaction = buildTransaction(prevPathElement);
        Connection connection = new Connection(docType, prevTransaction, docType.getTransactionId());
    }

    private Transaction buildTransaction(PathElement pathElement) {
        Transaction transaction = new Transaction();
        transaction.setName(pathElement.getTransactionName());
        transaction.setRoleId(pathElement.getAssingnedRole());
        return transaction;
    }

}
