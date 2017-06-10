package net.podolanski.service;

import net.podolanski.dao.Connection;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Transaction;
import net.podolanski.dao.repository.ConnectionRepository;
import net.podolanski.dto.DocumentPathForm;
import net.podolanski.dto.PathElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by maciej on 23.05.17.
 */

@Service
public class ConnectionService {

    @Autowired ConnectionRepository connectionRepository;

    public DocumentPathForm getConnectionToEdit(Doctype doctype) {
        List<Connection> connectionList = connectionRepository.findByDoctype(doctype);
        return connectionListToDocumentPathForm(connectionList, doctype);
    }

    public List<Connection> findByDocType(Doctype doctype) {
        return connectionRepository.findByDoctype(doctype);
    }

    public void remove(Connection connection) {
        connectionRepository.delete(connection);
    }

    public void removeConnection(Transaction transaction, Doctype doctype) {
        remove(connectionRepository.findByDoctypeAndTransaction(doctype, transaction));
    }

    public void save(Connection connection) {
        connectionRepository.save(connection);
    }

    public void save(Iterable<Connection> collection) {
        connectionRepository.save(collection);
    }

    private DocumentPathForm connectionListToDocumentPathForm(List<Connection> connectionList, Doctype doctype) {
        DocumentPathForm documentPathForm = new DocumentPathForm();
        documentPathForm.setDocumentName(doctype.getName());
        List<PathElement> pathElements = new ArrayList<>();
        for (Connection connection : connectionList) {
            Transaction transaction = connection.getTransaction();
            pathElements.add(new PathElement(transaction.getName(), transaction.getRoleId()));
        }
        documentPathForm.setPathElementList(pathElements);
        return documentPathForm;
    }

    public List<Connection> getSortedConnections(Doctype doctype) {
        List<Connection> connectionList = connectionRepository.findByDoctype(doctype);

        Comparator<Connection> comparator = (o1, o2) -> {
            return o1.getTransaction().equals(doctype.getTransactionId()) ? -1: 1;
        };
        connectionList.sort(comparator.thenComparing((o1, o2) -> {
            return o1.getTransaction1().equals(o2.getTransaction()) ? 1: -1;
        }));
        return  connectionList;
    }

    public void setConnectionEndPoint(Doctype doctype) {
        List<Connection> hello = getSortedConnections(doctype);
        Connection connection = hello.get(hello.size() - 1);
        connection.setTransaction1(doctype.getTransactionId());
        save(connection);
    }
}
