/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author maciej
 */
@Embeddable
public class CurrentStatePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "currentstate_id")
    private Integer currentstateId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "request_id")
    private int requestId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id")
    private int transactionId;

    public CurrentStatePK() {
    }

    public CurrentStatePK(int currentstateId, int requestId, int transactionId) {
        this.currentstateId = currentstateId;
        this.requestId = requestId;
        this.transactionId = transactionId;
    }

    public int getCurrentstateId() {
        return currentstateId;
    }

    public void setCurrentstateId(Integer currentstateId) {
        this.currentstateId = currentstateId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) currentstateId;
        hash += (int) requestId;
        hash += (int) transactionId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CurrentStatePK)) {
            return false;
        }
        CurrentStatePK other = (CurrentStatePK) object;
        if (this.currentstateId != other.currentstateId) {
            return false;
        }
        if (this.requestId != other.requestId) {
            return false;
        }
        if (this.transactionId != other.transactionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.podolanski.dao.CurrentStatePK[ currentstateId=" + currentstateId + ", requestId=" + requestId + ", transactionId=" + transactionId + " ]";
    }
    
}
