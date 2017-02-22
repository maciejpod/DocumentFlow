/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maciej
 */
@Entity
@Table(name = "t_connection")
@XmlRootElement
public class Connection implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConnectionPK connectionPK;
    @JoinColumn(name = "doctype_id", referencedColumnName = "doctype_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Doctype doctype;
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Transaction transaction;
    @JoinColumn(name = "ref_transaction_id", referencedColumnName = "transaction_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Transaction transaction1;

    public Connection() {
    }

    public Connection(ConnectionPK connectionPK) {
        this.connectionPK = connectionPK;
    }

    public Connection(int transactionId, int refTransactionId, int doctypeId) {
        this.connectionPK = new ConnectionPK(transactionId, refTransactionId, doctypeId);
    }

    public ConnectionPK getConnectionPK() {
        return connectionPK;
    }

    public void setConnectionPK(ConnectionPK connectionPK) {
        this.connectionPK = connectionPK;
    }

    public Doctype getDoctype() {
        return doctype;
    }

    public void setDoctype(Doctype doctype) {
        this.doctype = doctype;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction1() {
        return transaction1;
    }

    public void setTransaction1(Transaction transaction1) {
        this.transaction1 = transaction1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (connectionPK != null ? connectionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Connection)) {
            return false;
        }
        Connection other = (Connection) object;
        if ((this.connectionPK == null && other.connectionPK != null) || (this.connectionPK != null && !this.connectionPK.equals(other.connectionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.podolanski.dao.Connection[ connectionPK=" + connectionPK + " ]";
    }
    
}
