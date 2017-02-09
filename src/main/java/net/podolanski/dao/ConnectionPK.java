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
public class ConnectionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id")
    private Integer transactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ref_transaction_id")
    private int refTransactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doctype_id")
    private int doctypeId;

    public ConnectionPK() {
    }

    public ConnectionPK(int transactionId, int refTransactionId, int doctypeId) {
        this.transactionId = transactionId;
        this.refTransactionId = refTransactionId;
        this.doctypeId = doctypeId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getRefTransactionId() {
        return refTransactionId;
    }

    public void setRefTransactionId(int refTransactionId) {
        this.refTransactionId = refTransactionId;
    }

    public int getDoctypeId() {
        return doctypeId;
    }

    public void setDoctypeId(int doctypeId) {
        this.doctypeId = doctypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) transactionId;
        hash += (int) refTransactionId;
        hash += (int) doctypeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConnectionPK)) {
            return false;
        }
        ConnectionPK other = (ConnectionPK) object;
        if (this.transactionId != other.transactionId) {
            return false;
        }
        if (this.refTransactionId != other.refTransactionId) {
            return false;
        }
        if (this.doctypeId != other.doctypeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.podolanski.dao.ConnectionPK[ transactionId=" + transactionId + ", refTransactionId=" + refTransactionId + ", doctypeId=" + doctypeId + " ]";
    }
    
}
