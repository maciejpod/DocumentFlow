/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.podolanski.dao.Connection;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Transaction;
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

    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired TransactionRepository transactionRepository;
    @Autowired ConnectionService connectionService;
    @Autowired DoctypeService doctypeService;

    public List<Transaction> getDocumentFlow(Doctype doctype) {        
        return connectionService.getSortedConnections(doctype)
                .stream()
                .map((n) -> n.getTransaction())
                .collect(Collectors.toList());
    }

    public void createNewDocumentPath(Iterator<PathElement> pathElementIterator, Doctype docType, String documentName) {
        Doctype _docType = docType;
        PathElement prevPathElement = pathElementIterator.next();
        List<Connection> connections = new ArrayList<>();
        while(pathElementIterator.hasNext()) {
            PathElement currentPathElement = pathElementIterator.next();
            Transaction prevTransaction = buildTransaction(prevPathElement);
            Transaction nextTransaction = buildTransaction(currentPathElement);
            if(_docType == null) {
                _docType = doctypeService.createNewDocType(documentName, prevTransaction);
            }
            Connection connection = new Connection(_docType, prevTransaction, nextTransaction);
            prevPathElement = currentPathElement;
            connections.add(connection);
        }
        Transaction prevTransaction = buildTransaction(prevPathElement);
        connections.add(new Connection(_docType, prevTransaction, _docType.getTransactionId()));
        connectionService.save(connections);
        for(Connection connection: connectionService.getSortedConnections(_docType)) {
            logger.info("f: " + connection.getTransaction().getName() + " n: " + connection.getTransaction1().getName() + " d: " + connection.getDoctype().getName());
        }
    }

    /**
     *
     * @param doctype
     * @param documentEditForm
     */

    public void editExistingDocumentPath(Doctype doctype, DocumentPathForm documentEditForm) {
        List<Connection> connectionList = connectionService.getSortedConnections(doctype);
        doctype.setName(documentEditForm.getDocumentName());
        Iterator<PathElement> pathElementIter = documentEditForm.getPathElementList().iterator();
        Iterator<Transaction> transactionIter = connectionList.stream().map(n -> n.getTransaction()).collect(Collectors.toList()).iterator();

        while(pathElementIter.hasNext()) {
            if(transactionIter.hasNext()) {
                editExistingTransaction(pathElementIter.next(), transactionIter.next());
            } else {
                createNewDocumentPath(pathElementIter, doctype, doctype.getName());
            }
        }
        while (transactionIter.hasNext()) {
            connectionService.removeConnection(transactionIter.next(), doctype);
        }
        connectionService.setConnectionEndPoint(doctype);
    }

    private Transaction buildTransaction(PathElement pathElement) {
        Transaction transaction = new Transaction();
        transaction.setName(pathElement.getTransactionName());
        transaction.setRoleId(pathElement.getAssingnedRole());
        save(transaction);
        return transaction;
    }

    private void editExistingTransaction(PathElement pathElement, Transaction transaction) {
        transaction.setName(pathElement.getTransactionName());
        transaction.setRoleId(pathElement.getAssingnedRole());
        save(transaction);
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

}
