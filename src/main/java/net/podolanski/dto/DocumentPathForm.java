package net.podolanski.dto;

import net.podolanski.dao.Role;
import net.podolanski.dao.Transaction;

import java.util.List;
import java.util.Map;


/**
 * Created by maciej on 19.05.17.
 */
public class DocumentPathForm {
    String documentName;
    List<PathElement> pathElementList;

    public DocumentPathForm() {
    }

    public DocumentPathForm(String documentName, List<PathElement> pathElementList) {
        this.documentName = documentName;
        this.pathElementList = pathElementList;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public List<PathElement> getPathElementList() {
        return pathElementList;
    }

    public void setPathElementList(List<PathElement> pathElementList) {
        this.pathElementList = pathElementList;
    }
}
