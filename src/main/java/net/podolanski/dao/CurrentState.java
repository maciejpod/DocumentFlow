/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maciej
 */
@Entity
@Table(name = "t_current_state")
@XmlRootElement
public class CurrentState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "currentstate_id")
    private Integer currentStateId;
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @ManyToOne
    private Department department;
    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
    @ManyToOne(optional = false)
    private Request request;
    @Column(name = "status_id")
    @Enumerated(EnumType.ORDINAL)
    private Status statusId;
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    @ManyToOne(optional = false)
    private Transaction transaction;

    public CurrentState() {
    }
    
    public CurrentState(Request request, Transaction transaction, Department department) {
        this.statusId = Status.PROCESSING;
        this.request = request;
        this.transaction = transaction;
        this.department = department;
    }

    public CurrentState(Integer currentStateId) {
        this.currentStateId = currentStateId;
    }

    public Integer getCurrentStateId() {
        return currentStateId;
    }

    public void setCurrentStateId(Integer currentStateId) {
        this.currentStateId = currentStateId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (currentStateId != null ? currentStateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CurrentState)) {
            return false;
        }
        CurrentState other = (CurrentState) object;
        if ((this.currentStateId == null && other.currentStateId != null) || (this.currentStateId != null && !this.currentStateId.equals(other.currentStateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.podolanski.dao.CurrentState[ currentStatePK=" + currentStateId + " ]";
    }
    
}
