/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maciej
 */
@Entity
@Table(name = "\"T_REQUEST\"")
@XmlRootElement
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "request_id")
    private Integer requestId;
    @Basic(optional = false)
    @Column(name = "modified_flag")
    private boolean modifiedFlag;
    @Column(name = "feedback")
    private String feedback;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestId")
    private Collection<History> historyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
    private Collection<CurrentState> currentStateCollection;
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @ManyToOne
    private Department department;
    @JoinColumn(name = "doctype_id", referencedColumnName = "doctype_id")
    @ManyToOne
    private Doctype docType;
    @Column(name = "status_id")
    @Enumerated(EnumType.ORDINAL)
    private Status statusId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private User user;

    public Request() {
        this.modifiedFlag = false;
        this.statusId = Status.W_trakcie;
    }

    public Request(Integer requestId) {
        this.requestId = requestId;
    }

    public Request(User user, String content,
            Department department, Doctype docType) {
        this();
        this.content = content;
        this.docType = docType;
        this.department = department;
        this.user = user;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public boolean getModifiedFlag() {
        return modifiedFlag;
    }

    public void setModifiedFlag(boolean modifiedFlag) {
        this.modifiedFlag = modifiedFlag;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlTransient
    public Collection<History> getHistoryCollection() {
        return historyCollection;
    }

    public void setHistoryCollection(Collection<History> historyCollection) {
        this.historyCollection = historyCollection;
    }

    @XmlTransient
    public Collection<CurrentState> getCurrentStateCollection() {
        return currentStateCollection;
    }

    public void setCurrentStateCollection(Collection<CurrentState> currentStateCollection) {
        this.currentStateCollection = currentStateCollection;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Doctype getDocType() {
        return docType;
    }

    public void setDocType(Doctype docType) {
        this.docType = docType;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.podolanski.dao.Request[ requestId=" + requestId +
                " content=" + content + " status=" + statusId +" ]";
    }
    
}
