package net.podolanski.service;

import net.podolanski.dao.Connection;
import net.podolanski.dao.Doctype;
import net.podolanski.dao.Transaction;
import net.podolanski.dao.repository.ConnectionRepository;
import net.podolanski.dto.DocumentPathForm;
import net.podolanski.dto.PathElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
