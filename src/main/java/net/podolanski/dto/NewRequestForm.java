/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dto;

import net.podolanski.dao.Department;
import net.podolanski.dao.Doctype;

/**
 *
 * @author maciej
 */
public class NewRequestForm {

    private String content;

    private Department department;

    private Doctype docType;

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * @return the docType
     */
    public Doctype getDocType() {
        return docType;
    }

    /**
     * @param docType the docType to set
     */
    public void setDocType(Doctype docType) {
        this.docType = docType;
    }
}
